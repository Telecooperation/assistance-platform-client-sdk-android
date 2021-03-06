package de.tudarmstadt.informatik.tk.assistance.sdk.provider;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

import de.tudarmstadt.informatik.tk.assistance.sdk.service.AssistanceAccessibilityService;
import de.tudarmstadt.informatik.tk.assistance.sdk.service.HarvesterService;
import de.tudarmstadt.informatik.tk.assistance.sdk.service.PlanBService;
import de.tudarmstadt.informatik.tk.assistance.sdk.util.ServiceUtils;
import de.tudarmstadt.informatik.tk.assistance.sdk.util.logger.Log;

/**
 * @author Karsten Planz
 * @author Wladimir Schmidt (wlsc.dev@gmail.com)
 * @date 28.06.2015
 */
public final class HarvesterServiceProvider implements ServiceConnection {

    private static final String TAG = HarvesterServiceProvider.class.getSimpleName();

    private static HarvesterServiceProvider INSTANCE;

    private static Context mContext;

    private static ModuleProvider moduleProvider;
    private static SensorProvider sensorProvider;

    private Messenger mMessengerOutgoing = null;
    private Messenger mMessengerIncoming = new Messenger(new IncomingMessageHandler(Looper.getMainLooper()));

    private boolean isServiceBound;

    // connection to sensing service
    private ServiceConnection mServiceConnection;

    private Intent serviceIntent;

    private HarvesterServiceProvider(Context context) {

        mContext = context;
        mServiceConnection = this;
        serviceIntent = new Intent(mContext, HarvesterService.class);

        if (!isServiceBound) {
            bindService();
        }

        sensorProvider = SensorProvider.getInstance(context);
        moduleProvider = ModuleProvider.getInstance(context);
    }

    public static HarvesterServiceProvider getInstance(Context context) {

        if (INSTANCE == null) {
            INSTANCE = new HarvesterServiceProvider(context);
        }

        mContext = context;

        return INSTANCE;
    }

    public boolean isServiceRunning() {
        return ServiceUtils.isServiceRunning(mContext, HarvesterService.class);
    }

    /**
     * Checks if Accessibility Service is running
     *
     * @return
     */
    public boolean isAccessibilityServiceRunning() {
        return ServiceUtils.isServiceRunning(mContext, AssistanceAccessibilityService.class);
    }

    /**
     * Starts sensing service
     */
    public void startSensingService() {

        if (!ServiceUtils.isHarvesterAbleToRun(mContext.getApplicationContext())) {
            Log.d(TAG, "Sensing service was not able to run (no active modules?)");
            return;
        }

        if (!isServiceRunning()) {

            mContext.startService(serviceIntent);
            startAccessibilityService();

        } else {

            bindService();
            sendMessageToService(HarvesterService.MSG_CMD_START_SERVICE);
            sensorProvider.synchronizeRunningSensorsWithDb();
            showHarvestIcon(true);
        }
    }

    /**
     * Shows harvester icon or not
     *
     * @param showIcon
     */
    public void showHarvestIcon(boolean showIcon) {

        if (showIcon) {
            sendMessageToService(HarvesterService.MSG_CMD_SHOW_ICON);
        } else {
            sendMessageToService(HarvesterService.MSG_CMD_HIDE_ICON);
        }
    }

    /**
     * Stops sensing service
     */
    public void stopSensingService() {

        sendMessageToService(HarvesterService.MSG_CMD_STOP_SERVICE);

        if (isServiceBound) {
            unbindService();
        }

        stopAccessibilityService();
    }

    /**
     * Starts Accessibility Service
     */
    public void startAccessibilityService() {

        Log.d(TAG, "Starting Accessibility Service...");

        Intent intent = new Intent(mContext, AssistanceAccessibilityService.class);
        mContext.startService(intent);
    }

    /**
     * Stops Accessibility Service
     */
    public void stopAccessibilityService() {

        Log.d(TAG, "Stopping Accessibility Service...");

        Intent intent = new Intent(mContext, AssistanceAccessibilityService.class);
        mContext.stopService(intent);
    }

    /**
     * Starts PlanB Service to watch for Harvester Service instance
     */
    public void startPlanBService() {

        Intent intent = new Intent(mContext, PlanBService.class);
        mContext.startService(intent);
    }

    /**
     * Stops PlanB Service
     */
    public void stopPlanBService() {

        Intent intent = new Intent(mContext, PlanBService.class);
        mContext.stopService(intent);
    }

    public boolean isServiceBound() {
        return isServiceBound;
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {

        Log.d(TAG, "Service is connected to binder");

        isServiceBound = true;

        mMessengerOutgoing = new Messenger(service);

        sendMessageToService(HarvesterService.MSG_CMD_REGISTER_CLIENT);
        sendMessageToService(HarvesterService.MSG_CMD_START_SERVICE);
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

        isServiceBound = false;
        mMessengerOutgoing = null;

        Log.d(TAG, "Sensing service has been disconnected.");
    }

    /**
     * Sends command to sensing service
     *
     * @param command
     */
    public void sendMessageToService(int command) {

        if (isServiceBound) {
            if (mMessengerOutgoing != null) {

                try {

                    Message msg = Message.obtain(null, command);

                    msg.replyTo = mMessengerIncoming;
                    mMessengerOutgoing.send(msg);

                } catch (RemoteException e) {
                    Log.e(TAG, "Cannot send message to service!");
                }
            } else {
                Log.d(TAG, "Service: outgoing canal is null");
            }
        } else {
            Log.d(TAG, "Service is not bound. Cannot send command. Please bind the service");
        }
    }

    /**
     * Binds this provider to sensing service
     */
    private void bindService() {

        if (!isServiceBound) {

            try {

                mContext.bindService(
                        serviceIntent,
                        mServiceConnection,
                        Context.BIND_AUTO_CREATE
                );

                isServiceBound = true;

            } catch (Exception e) {
                Log.e(TAG, "Some error.");
            }
        } else {
            Log.d(TAG, "Service is already bound.");
        }
    }

    /**
     * Unbinds this provider from sensing service
     */
    public void unbindService() {

        if (isServiceBound) {

            sendMessageToService(HarvesterService.MSG_CMD_UNREGISTER_CLIENT);

            try {
                // disconnecting...
                mContext.unbindService(mServiceConnection);
            } catch (IllegalArgumentException iae) {
                Log.d(TAG, "Service was not bound!");
            } finally {
                this.isServiceBound = false;
            }

            Log.d(TAG, "The service provider was successfully unbound from sensing service!");
        }
    }

    /**
     * Handle incoming messages
     */
    private static class IncomingMessageHandler extends Handler {

        public IncomingMessageHandler(Looper mainLooper) {
            super(mainLooper);
        }

        @Override
        public void handleMessage(Message msg) {
            // empty
        }
    }
}