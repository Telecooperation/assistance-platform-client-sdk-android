package de.tudarmstadt.informatik.tk.android.kraken.service;

import android.content.Context;
import android.util.Log;
import android.util.SparseArray;

import com.google.android.gms.gcm.GcmNetworkManager;
import com.google.android.gms.gcm.GcmTaskService;
import com.google.android.gms.gcm.PeriodicTask;
import com.google.android.gms.gcm.Task;
import com.google.android.gms.gcm.TaskParams;

import java.util.LinkedList;
import java.util.List;

import de.tudarmstadt.informatik.tk.android.kraken.PreferenceManager;
import de.tudarmstadt.informatik.tk.android.kraken.ServiceManager;
import de.tudarmstadt.informatik.tk.android.kraken.model.api.EventUploadRequest;
import de.tudarmstadt.informatik.tk.android.kraken.model.api.endpoint.EventUploadEndpoint;
import de.tudarmstadt.informatik.tk.android.kraken.model.api.endpoint.ServiceGenerator;
import de.tudarmstadt.informatik.tk.android.kraken.model.sensor.Sensor;
import de.tudarmstadt.informatik.tk.android.kraken.provider.DbProvider;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * @author Wladimir Schmidt (wlsc.dev@gmail.com)
 * @date 07.10.2015
 */
public class EventUploaderService extends GcmTaskService {

    private static final String TAG = EventUploaderService.class.getSimpleName();

    private static final int PUSH_NUMBER_OF_EACH_ELEMENTS = 20;

    // task identifier
    private long taskID = 1;
    // the task should be executed every 30 seconds
    private long periodSecs = 5L;
    // the task can run as early as -15 seconds from the scheduled time
    private long flexSecs = 1L;

    // an unique task identifier
    private String taskTag = "periodic | " + taskID++ + ": " + periodSecs + "s, f:" + flexSecs;

    private ServiceManager serviceManager;

    private static PreferenceManager mPreferenceManager;

    private SparseArray<List<Sensor>> events;

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d(TAG, "Initializing...");

        if (mPreferenceManager == null) {
            mPreferenceManager = PreferenceManager.getInstance(getApplicationContext());
        }

        Log.d(TAG, "Finished!");
    }

    @Override
    public int onRunTask(TaskParams taskParams) {

        Log.d(TAG, "Upload task has started");

        EventUploadRequest eventUploadRequest = new EventUploadRequest();

        long serverDeviceId = mPreferenceManager.getServerDeviceId();

        Log.d(TAG, "Sync server device id: " + serverDeviceId);

        events = new SparseArray<>();
        events = DbProvider.getInstance(getApplicationContext()).getEntriesForUpload(PUSH_NUMBER_OF_EACH_ELEMENTS);

        List<Sensor> eventsAsList = new LinkedList<>();

        for (int i = 0; i < events.size(); i++) {
            int key = events.keyAt(i);
            eventsAsList.addAll(events.get(key));
        }

        eventUploadRequest.setDataEvents(eventsAsList);
        eventUploadRequest.setServerDeviceId(serverDeviceId);

        doUploadEventData(eventUploadRequest);

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
            return;
        }

        if (eventUploadRequest.getDataEvents().size() == 0) {
            return;
        }

        // send to upload data service
        EventUploadEndpoint eventUploadEndpoint = ServiceGenerator.createService(EventUploadEndpoint.class);

        String userToken = mPreferenceManager.getUserToken();

        eventUploadEndpoint.uploadData(userToken, eventUploadRequest, new Callback<Void>() {

            @Override
            public void success(Void aVoid, Response response) {

                if (response != null && (response.getStatus() == 200 || response.getStatus() == 204)) {

                    Log.d(TAG, "OK response from server received");

                    // successful transmission of event data -> remove that data from db
                    DbProvider.getInstance(getApplicationContext()).removeDbSentEvents(events);

                }
            }

            @Override
            public void failure(RetrofitError error) {
                // TODO process error
                Log.d(TAG, "Server returned error!");
            }
        });
    }

    @Override
    public void onInitializeTasks() {

        Log.d(TAG, "Reinitialize periodic task...");

        // first cancel any running event uploader tasks
        GcmNetworkManager.getInstance(getApplicationContext()).cancelAllTasks(EventUploaderService.class);
        schedulePeriodicTask(getApplicationContext(), periodSecs, flexSecs, taskTag);

        super.onInitializeTasks();
    }

    /**
     * Schedules an periodic upload task
     */
    public static void schedulePeriodicTask(Context context, long periodSecs, long flexSecs, String tag) {

        PeriodicTask periodicTask = new PeriodicTask.Builder()
                .setService(EventUploaderService.class)
                .setPeriod(periodSecs)
                .setFlex(flexSecs)
                .setTag(tag)
                .setPersisted(true)
                .setRequiredNetwork(Task.NETWORK_STATE_ANY)
                .setRequiresCharging(false)
                .build();

        GcmNetworkManager.getInstance(context).schedule(periodicTask);
    }
}