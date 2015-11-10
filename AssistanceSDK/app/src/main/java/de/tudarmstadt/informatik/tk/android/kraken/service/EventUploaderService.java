package de.tudarmstadt.informatik.tk.android.kraken.service;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.google.android.gms.gcm.GcmNetworkManager;
import com.google.android.gms.gcm.GcmTaskService;
import com.google.android.gms.gcm.OneoffTask;
import com.google.android.gms.gcm.PeriodicTask;
import com.google.android.gms.gcm.Task;
import com.google.android.gms.gcm.TaskParams;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import de.tudarmstadt.informatik.tk.android.kraken.interfaces.IDbSensor;
import de.tudarmstadt.informatik.tk.android.kraken.model.api.EventUploadRequest;
import de.tudarmstadt.informatik.tk.android.kraken.model.api.dto.DtoType;
import de.tudarmstadt.informatik.tk.android.kraken.model.api.endpoint.EndpointGenerator;
import de.tudarmstadt.informatik.tk.android.kraken.model.api.endpoint.EventUploadEndpoint;
import de.tudarmstadt.informatik.tk.android.kraken.model.sensor.Sensor;
import de.tudarmstadt.informatik.tk.android.kraken.provider.DaoProvider;
import de.tudarmstadt.informatik.tk.android.kraken.provider.PreferenceProvider;
import de.tudarmstadt.informatik.tk.android.kraken.util.ConnectionUtils;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * @author Wladimir Schmidt (wlsc.dev@gmail.com)
 * @date 07.10.2015
 */
public class EventUploaderService extends GcmTaskService {

    private static final String TAG = EventUploaderService.class.getSimpleName();

    private static final int EVENTS_NUMBER_TO_SPLIT_AFTER = 500;
    private static final int PUSH_NUMBER_OF_EACH_ELEMENTS = 80;

    // task identifier
    private static final long taskID = 999;
    // the task should be executed every 30 seconds
    private static final long periodSecs = 60L;
    // fallback for period in case of server is not available
    private static final long periodServerNotAvailableFallbackSecs = 300L;
    // the task can run as early as -15 seconds from the scheduled time
    private static final long flexSecs = 15L;
    // fallback for flexibility in case of server not available
    private static final long flexServerNotAvailableFallbackSecs = 100L;

    // an unique default task identifier
    private static String taskTagDefault = "periodic | " +
            taskID + ": " +
            periodSecs + "s, f:" +
            flexSecs;

    // an unique connection fallback task identifier
    private static String taskTagFallback = "periodic | " +
            taskID + ": " +
            periodServerNotAvailableFallbackSecs +
            "s, f:" +
            flexServerNotAvailableFallbackSecs;

    private static final String UPLOAD_ALL_FLAG_NAME = "UPLOAD_ALL";
    private static final int UPLOAD_ALL_FLAG = 1;

    private static PreferenceProvider mPreferenceProvider;

    private Map<Integer, List<? extends IDbSensor>> requestDbEvents;
    private Map<Integer, List<Sensor>> requestEvents;

    private static boolean isNeedInConnectionFallback;

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d(TAG, "Initializing...");

        if (mPreferenceProvider == null) {
            mPreferenceProvider = PreferenceProvider.getInstance(getApplicationContext());
        }

        String userToken = mPreferenceProvider.getUserToken();

        if (userToken != null && !userToken.isEmpty()) {

            cancelAllTasks(getApplicationContext());

            if (isNeedInConnectionFallback) {
                schedulePeriodicTask(getApplicationContext(),
                        periodServerNotAvailableFallbackSecs,
                        flexServerNotAvailableFallbackSecs,
                        taskTagFallback);
            } else {
                schedulePeriodicTask(getApplicationContext(), periodSecs, flexSecs, taskTagDefault);
            }
        } else {
            Log.d(TAG, "User is not logged in. Scheduled task won't start");
        }

        Log.d(TAG, "Finished!");
    }

    @Override
    public int onRunTask(TaskParams taskParams) {

        Log.d(TAG, "Task uploader has started");

        // check Airplane Mode enabled
        if (ConnectionUtils.isAirplaneModeEnabled(getApplicationContext())) {
            Log.d(TAG, "Airplane Mode enabled. Upload request ignored");
            return GcmNetworkManager.RESULT_FAILURE;
        }

        // device is not online
        if (!ConnectionUtils.isOnline(getApplicationContext())) {
            Log.d(TAG, "Device is not online. Upload request ignored");
            return GcmNetworkManager.RESULT_FAILURE;
        }

        boolean isPeriodic = true;

        if (taskParams != null) {

            Bundle extras = taskParams.getExtras();

            if (extras != null) {

                int value = extras.getInt(UPLOAD_ALL_FLAG_NAME, 0);

                // upload all data at once
                if (value == UPLOAD_ALL_FLAG) {

                    isPeriodic = false;

                    Handler handler = new Handler(getMainLooper());
                    handler.post(new Runnable() {

                        @Override
                        public void run() {

                            final long serverDeviceId = mPreferenceProvider.getServerDeviceId();

                            Log.d(TAG, "Sync server device id: " + serverDeviceId);

                            // user logged out
                            if (serverDeviceId == -1) {
                                Log.d(TAG, "User logged out -- all tasks has been canceled!");
                                GcmNetworkManager.getInstance(getApplicationContext())
                                        .cancelAllTasks(EventUploaderService.class);
                                return;
                            }

                            requestEvents = new HashMap<>();
                            requestDbEvents = new HashMap<>();
                            requestEvents = getEntriesForUpload(0);

                            final List<Sensor> eventsAsList = new LinkedList<>();

                            for (Map.Entry<Integer, List<Sensor>> entry : requestEvents.entrySet()) {

                                eventsAsList.addAll(entry.getValue());
                            }

                            // partial upload
                            int eventsSize = eventsAsList.size();

                            Log.d(TAG, "There are " + eventsSize + " events to upload");

                            if (eventsSize < EVENTS_NUMBER_TO_SPLIT_AFTER) {
                                // send as usual

                                Log.d(TAG, "Sending data in normal mode");

                                EventUploadRequest eventUploadRequest = new EventUploadRequest();
                                eventUploadRequest.setDataEvents(eventsAsList);
                                eventUploadRequest.setServerDeviceId(serverDeviceId);

                                doUploadEventData(eventUploadRequest);

                            } else {
                                // send partial with many requests
                                int howMuchToSend = eventsSize / EVENTS_NUMBER_TO_SPLIT_AFTER;

                                Log.d(TAG, "Sending partial data with " + (howMuchToSend + 1) + " requests");

                                for (int i = 0; i <= howMuchToSend; i++) {

                                    final int finalCounter = i;
                                    int lastElementIndex = (finalCounter + 1) * EVENTS_NUMBER_TO_SPLIT_AFTER;

                                    // assign last element index
                                    if (i == howMuchToSend) {
                                        lastElementIndex = eventsSize - 1;
                                    }

                                    final int finalLastElementIndex = lastElementIndex;

                                    AsyncTask.execute(new Runnable() {

                                        @Override
                                        public void run() {

                                            List<Sensor> tmpSensors = eventsAsList
                                                    .subList(finalCounter * EVENTS_NUMBER_TO_SPLIT_AFTER,
                                                            finalLastElementIndex);

                                            EventUploadRequest eventUploadRequest = new EventUploadRequest();
                                            eventUploadRequest.setDataEvents(tmpSensors);
                                            eventUploadRequest.setServerDeviceId(serverDeviceId);

                                            doUploadEventData(eventUploadRequest);
                                        }
                                    });
                                }
                            }
                        }
                    });
                }
            }
        }

        // do periodic upload task
        if (isPeriodic) {

            Handler handler = new Handler(getMainLooper());
            handler.post(new Runnable() {

                @Override
                public void run() {

                    final long serverDeviceId = mPreferenceProvider.getServerDeviceId();

                    Log.d(TAG, "Sync server device id: " + serverDeviceId);

                    // user logged out
                    if (serverDeviceId == -1) {
                        Log.d(TAG, "User logged out -- all tasks has been canceled!");
                        GcmNetworkManager.getInstance(getApplicationContext())
                                .cancelAllTasks(EventUploaderService.class);
                        return;
                    }

                    requestEvents = new HashMap<>();
                    requestDbEvents = new HashMap<>();
                    requestEvents = getEntriesForUpload(PUSH_NUMBER_OF_EACH_ELEMENTS);

                    final List<Sensor> eventsAsList = new LinkedList<>();

                    for (Map.Entry<Integer, List<Sensor>> entry : requestEvents.entrySet()) {

                        eventsAsList.addAll(entry.getValue());
                    }

                    // partial upload
                    int eventsSize = eventsAsList.size();

                    Log.d(TAG, "There are " + eventsSize + " events to upload");

                    if (eventsSize < EVENTS_NUMBER_TO_SPLIT_AFTER) {
                        // send as usual

                        Log.d(TAG, "Sending data in normal mode");

                        EventUploadRequest eventUploadRequest = new EventUploadRequest();
                        eventUploadRequest.setDataEvents(eventsAsList);
                        eventUploadRequest.setServerDeviceId(serverDeviceId);

                        doUploadEventData(eventUploadRequest);

                    } else {
                        // send partial with many requests
                        int howMuchToSend = eventsSize / EVENTS_NUMBER_TO_SPLIT_AFTER;

                        Log.d(TAG, "Sending partial data with " + (howMuchToSend + 1) + " requests");

                        for (int i = 0; i <= howMuchToSend; i++) {

                            final int finalCounter = i;
                            int lastElementIndex = (finalCounter + 1) * EVENTS_NUMBER_TO_SPLIT_AFTER;

                            // assign last element index
                            if (i == howMuchToSend) {
                                lastElementIndex = eventsSize - 1;
                            }

                            final int finalLastElementIndex = lastElementIndex;

                            AsyncTask.execute(new Runnable() {

                                @Override
                                public void run() {

                                    List<Sensor> tmpSensors = eventsAsList
                                            .subList(finalCounter * EVENTS_NUMBER_TO_SPLIT_AFTER,
                                                    finalLastElementIndex);

                                    EventUploadRequest eventUploadRequest = new EventUploadRequest();
                                    eventUploadRequest.setDataEvents(tmpSensors);
                                    eventUploadRequest.setServerDeviceId(serverDeviceId);

                                    doUploadEventData(eventUploadRequest);
                                }
                            });
                        }
                    }
                }
            });
        }

        return GcmNetworkManager.RESULT_SUCCESS;
    }

    /**
     * Pushes events data to server
     *
     * @param eventUploadRequest
     */
    private void doUploadEventData(final EventUploadRequest eventUploadRequest) {

        Log.d(TAG, "Uploading data...");

        if (eventUploadRequest == null || eventUploadRequest.getDataEvents() == null) {
            Log.d(TAG, "eventUploadRequest is null or events list is empty!");
            return;
        }

        if (eventUploadRequest.getDataEvents().size() == 0) {
            Log.d(TAG, "No new data found");
            return;
        }

        // send to upload data service
        EventUploadEndpoint eventUploadEndpoint = EndpointGenerator
                .getInstance(getApplicationContext())
                .create(EventUploadEndpoint.class);

        String userToken = mPreferenceProvider.getUserToken();

        eventUploadEndpoint.uploadData(userToken, eventUploadRequest,
                new Callback<Void>() {

                    @Override
                    public void success(Void aVoid, Response response) {

                        if (response != null && (response.getStatus() == 200 || response.getStatus() == 204)) {

                            Log.d(TAG, "OK response from server received");

                            // successful transmission of event data -> remove that data from db
                            removeDbSentEvents(requestDbEvents);

                            // reschedule default periodic task
                            if (isNeedInConnectionFallback) {
                                isNeedInConnectionFallback = false;

                                rescheduleDefaultPeriodicTask();
                            }
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        // TODO process error
                        Log.d(TAG, "Server returned error! Kind: " + error.getKind().name());

                        // fallbacking periodic request
                        if (!isNeedInConnectionFallback) {
                            isNeedInConnectionFallback = true;

                            rescheduleFallbackPeriodicTask();
                        }
                    }
                });
    }

    /**
     * Rescheduling default task by canceling fallback one
     */
    private void rescheduleDefaultPeriodicTask() {

        Log.d(TAG, "Rescheduling default periodic task...");

        // cancelling fallback periodic task
        cancelByTag(getApplicationContext(), taskTagFallback);

        // reschedule periodic task with default timings
        schedulePeriodicTask(getApplicationContext(),
                periodSecs,
                flexSecs,
                taskTagDefault);
    }

    /**
     * Rescheduling fallback task by canceling default one
     */
    private void rescheduleFallbackPeriodicTask() {

        Log.d(TAG, "Rescheduling fallback periodic task...");

        // cancelling default periodic task
        cancelByTag(getApplicationContext(), taskTagDefault);

        // reschedule periodic task with fallback timings
        schedulePeriodicTask(getApplicationContext(),
                periodServerNotAvailableFallbackSecs,
                flexServerNotAvailableFallbackSecs,
                taskTagFallback);
    }

    @Override
    public void onInitializeTasks() {

        Log.d(TAG, "Reinitialize periodic task...");

        // first cancel any running event uploader tasks
        cancelAllTasks(getApplicationContext());

        if (isNeedInConnectionFallback) {
            schedulePeriodicTask(getApplicationContext(),
                    periodServerNotAvailableFallbackSecs,
                    flexServerNotAvailableFallbackSecs,
                    taskTagFallback);
        } else {
            schedulePeriodicTask(getApplicationContext(), periodSecs, flexSecs, taskTagDefault);
        }

        super.onInitializeTasks();
    }

    /**
     * Returns events for upload to server
     *
     * @param numberOfElements
     * @return
     */
    public Map<Integer, List<Sensor>> getEntriesForUpload(int numberOfElements) {

        Map<Integer, List<Sensor>> entries = new HashMap<>();

        DaoProvider daoProvider = DaoProvider.getInstance(getApplicationContext());

        List<? extends IDbSensor> dbAccelerometerList = daoProvider
                .getAccelerometerSensorDao()
                .getFirstN(numberOfElements);

        requestDbEvents.put(DtoType.ACCELEROMETER, dbAccelerometerList);

        entries.put(DtoType.ACCELEROMETER, daoProvider
                .getAccelerometerSensorDao()
                .convertObjects(dbAccelerometerList));

        List<? extends IDbSensor> dbLocationList = daoProvider
                .getLocationSensorDao()
                .getFirstN(numberOfElements);

        requestDbEvents.put(DtoType.LOCATION, dbLocationList);

        entries.put(DtoType.LOCATION, daoProvider
                .getLocationSensorDao()
                .convertObjects(dbLocationList));

        List<? extends IDbSensor> motionActivityList = daoProvider
                .getMotionActivityEventDao()
                .getFirstN(numberOfElements);

        requestDbEvents.put(DtoType.MOTION_ACTIVITY, motionActivityList);

        entries.put(DtoType.MOTION_ACTIVITY, daoProvider
                .getMotionActivityEventDao()
                .convertObjects(motionActivityList));

        List<? extends IDbSensor> foregroundEventList = daoProvider
                .getForegroundEventDao()
                .getFirstN(numberOfElements);

        requestDbEvents.put(DtoType.FOREGROUND, foregroundEventList);

        entries.put(DtoType.FOREGROUND, daoProvider
                .getForegroundEventDao()
                .convertObjects(foregroundEventList));

        return entries;
    }

    /**
     * Removes successful transmitted entries from database
     *
     * @param dbEvents
     */
    public void removeDbSentEvents(Map<Integer, List<? extends IDbSensor>> dbEvents) {

        Log.d(TAG, "Removing sent events from db...");

        for (Map.Entry<Integer, List<? extends IDbSensor>> entry : dbEvents.entrySet()) {

            int sensorType = entry.getKey();
            List<? extends IDbSensor> values = entry.getValue();

            if (values == null || values.isEmpty()) {
                continue;
            }

            DaoProvider daoProvider = DaoProvider.getInstance(getApplicationContext());

            switch (sensorType) {
                case DtoType.ACCELEROMETER:
                    daoProvider.getAccelerometerSensorDao().delete(values);
                    break;
                case DtoType.LOCATION:
                    daoProvider.getLocationSensorDao().delete(values);
                    break;
                case DtoType.MOTION_ACTIVITY:
                    daoProvider.getMotionActivityEventDao().delete(values);
                    break;
                case DtoType.FOREGROUND:
                    daoProvider.getForegroundEventDao().delete(values);
                    break;
            }
        }

        if (requestDbEvents != null) {
            requestDbEvents.clear();
        }

        if (requestEvents != null) {
            requestEvents.clear();
        }

        Log.d(TAG, "Finished removing data from db");
    }

    /**
     * Cancels all GCM Network Manager tasks
     */
    public static void cancelAllTasks(Context context) {
        GcmNetworkManager.getInstance(context).cancelAllTasks(EventUploaderService.class);
    }

    /**
     * Cancels default periodic task
     */
    public static void cancel(Context context) {
        GcmNetworkManager.getInstance(context).cancelTask(taskTagDefault, EventUploaderService.class);
    }

    /**
     * Cancels GCM Network Manager periodic task
     */
    public static void cancelByTag(Context context, String tag) {
        GcmNetworkManager.getInstance(context).cancelTask(tag, EventUploaderService.class);
    }

    /**
     * Schedules an periodic upload task
     */
    public static void schedulePeriodicTask(Context context, long paramPeriodSecs, long paramFlexSecs, String tag) {

        Log.d(TAG, "Scheduling periodic task...");

        PeriodicTask periodicTask = new PeriodicTask.Builder()
                .setService(EventUploaderService.class)
                .setPeriod(paramPeriodSecs)
                .setFlex(paramFlexSecs)
                .setTag(tag)
                .setPersisted(true)
                .setUpdateCurrent(true)
                .setRequiredNetwork(Task.NETWORK_STATE_ANY)
                .setRequiresCharging(false)
                .build();

        GcmNetworkManager.getInstance(context).schedule(periodicTask);

        Log.d(TAG, "Periodic task scheduled!");
    }

    /**
     * Schedules an periodic upload task
     */
    public static void scheduleOneTimeTask(Context context, long startSecs, long endSecs, String tag) {

        Log.d(TAG, "Scheduling one time task...");

        Bundle bundle = new Bundle();
        bundle.putInt(UPLOAD_ALL_FLAG_NAME, UPLOAD_ALL_FLAG);

        OneoffTask oneTimeTask = new OneoffTask.Builder()
                .setService(EventUploaderService.class)
                .setExecutionWindow(startSecs, endSecs)
                .setTag(tag)
                .setPersisted(true)
                .setUpdateCurrent(true)
                .setRequiredNetwork(Task.NETWORK_STATE_ANY)
                .setRequiresCharging(false)
                .setExtras(bundle)
                .build();

        GcmNetworkManager.getInstance(context).schedule(oneTimeTask);

        Log.d(TAG, "One time task was scheduled!");
    }
}
