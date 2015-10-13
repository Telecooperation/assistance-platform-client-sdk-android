package de.tudarmstadt.informatik.tk.android.kraken.provider;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;

import de.greenrobot.event.EventBus;
import de.tudarmstadt.informatik.tk.android.kraken.event.StartSensingEvent;
import de.tudarmstadt.informatik.tk.android.kraken.event.StopSensingEvent;
import de.tudarmstadt.informatik.tk.android.kraken.service.HarvesterService;

/**
 * @author Karsten Planz
 * @author Wladimir Schmidt (wlsc.dev@gmail.com)
 * @date 28.06.2015
 */
public class HarvesterServiceProvider implements Handler.Callback {

    private static final String TAG = HarvesterServiceProvider.class.getSimpleName();

    private static HarvesterServiceProvider INSTANCE;

    private final Context mContext;
    private final Intent mKrakenIntent;
    private Messenger mMessenger;

    private boolean isServiceBound = false;

//    protected ServiceConnection mServiceConnection = new ServiceConnection() {
//
//        private final String TAG = ServiceConnection.class.getSimpleName();
//
//        @Override
//        public void onServiceDisconnected(ComponentName name) {
//            isServiceBound = false;
//        }
//
//        @Override
//        public void onServiceConnected(ComponentName name, IBinder service) {
//
//            Log.d(TAG, "Service was connected to binder");
//
//            mMessenger = new Messenger(service);
//
//            KrakenService.sendCommand(
//                    mMessenger,
//                    ECommandType.SET_HANDLER,
//                    new Messenger(new Handler(KrakenServiceManager.this)));
//
//            isServiceBound = true;
//
//        }
//    };

    private HarvesterServiceProvider(Context context) {

        mContext = context;
        mKrakenIntent = new Intent(context, HarvesterService.class);

        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    public static HarvesterServiceProvider getInstance(Context context) {

        if (INSTANCE == null) {
            INSTANCE = new HarvesterServiceProvider(context);
        }

        return INSTANCE;
    }

    public boolean isServiceRunning() {

        ActivityManager manager = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);

        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (HarvesterService.class.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Starts sensing service
     */
    public void startService() {

        mContext.startService(mKrakenIntent);

        showIcon(true);

        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    /**
     * Stops sensing service
     */
    public void stopService() {

        mContext.stopService(mKrakenIntent);

        showIcon(false);

        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    public void showIcon(boolean show) {

        Intent intent = mKrakenIntent;
        intent.putExtra("showIcon", show);
        mContext.startService(intent);
    }

    public boolean isServiceBound() {
        return isServiceBound;
    }

    @Override
    public boolean handleMessage(Message msg) {
        return false;
    }

    /**
     * On start sensing event
     *
     * @param event
     */
    public void onEvent(StartSensingEvent event) {

        Log.d(TAG, "StartSensingEvent received");

        HarvesterServiceProvider service = getInstance(event.getContext());
        service.startService();
    }

    /**
     * On stop sensing event
     *
     * @param event
     */
    public void onEvent(StopSensingEvent event) {

        Log.d(TAG, "StopSensingEvent received");

        HarvesterServiceProvider service = getInstance(event.getContext());
        service.stopService();
    }
}
