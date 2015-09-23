package de.tudarmstadt.informatik.tk.android.kraken;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import de.tudarmstadt.informatik.tk.android.kraken.common.MessageType;
import de.tudarmstadt.informatik.tk.android.kraken.communication.IServerCommunicationResponseHandler;
import de.tudarmstadt.informatik.tk.android.kraken.communication.ServerCommunication;
import de.tudarmstadt.informatik.tk.android.kraken.util.KrakenUtils;

public class KrakenGcmManager {

    private static final String TAG = KrakenGcmManager.class.getSimpleName();

    private static final String PROPERTY_REG_ID = "GcmRegId";
    private static final String APP_VERSION = "AppVersion";
    protected static final String SENDER_ID = "930932669428";

    private static KrakenGcmManager instance;

    private static Context mContext;
    private GoogleCloudMessaging mGCM;
    private String registrationToken;

    private KrakenGcmManager(Context context) {
        this.mContext = context;
    }

    public static KrakenGcmManager getInstance(Context context) {

        if (instance == null) {
            instance = new KrakenGcmManager(context);
        }

        return instance;
    }

    public static KrakenGcmManager getInstance() {
        return instance;
    }

    public void registerAtCloud() {

        mGCM = GoogleCloudMessaging.getInstance(mContext);
        registrationToken = getRegistrationId(mContext);

        if (registrationToken.isEmpty()) {
            registerInBackground();
        }

    }

    private String getRegistrationId(Context context) {
        final SharedPreferences prefs = getGCMPreferences();
        String registrationId = prefs.getString(PROPERTY_REG_ID, "");
        if (registrationId.isEmpty()) {
            return "";
        }

        // Check if app was updated; if so, it must clear the registration ID
        // since the existing regID is not guaranteed to work with the new
        // app version.
        int registeredVersion = prefs.getInt(APP_VERSION, Integer.MIN_VALUE);
        int currentVersion = getAppVersion(context);
        if (registeredVersion != currentVersion) {
            return "";
        }
        return registrationId;
    }

    /**
     * @return Application's {@code SharedPreferences}.
     */
    private SharedPreferences getGCMPreferences() {
        return mContext.getSharedPreferences(KrakenSdkSettings.PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    /**
     * @return Application's version code from the {@code PackageManager}.
     */
    private static int getAppVersion(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (NameNotFoundException e) {
            // should never happen
            throw new RuntimeException("Could not get package name: " + e);
        }
    }

    /**
     * Registers the application with GCM servers asynchronously.
     * <p/>
     * Stores the registration ID and app versionCode in the application's
     * shared preferences.
     */
    private void registerInBackground() {

        new AsyncTask<Object, Object, Object>() {

            @Override
            protected Object doInBackground(Object... params) {
                String msg = "";
                try {
                    if (mGCM == null) {
                        mGCM = GoogleCloudMessaging.getInstance(mContext);
                    }
                    registrationToken = mGCM.register(SENDER_ID);
                    msg = "Device registered, registration ID=" + registrationToken;

                    // You should send the registration ID to your server over
                    // HTTP,
                    // so it can use GCM/HTTP or CCS to send messages to your
                    // app.
                    // The request to your server should be authenticated if
                    // your app
                    // is using accounts.
                    try {
                        sendRegistrationIdToBackend();
                    } catch (JSONException e) {
                        Log.e(TAG, "Cannot send registration id to server! Error: ",e);
                    }

                    // Persist the regID - no need to register again.
                } catch (IOException ex) {
                    msg = "Error :" + ex.getMessage();
                    // If there is an error, don't just keep trying to register.
                    // Require the user to click a button again, or perform
                    // exponential back-off.
                }
                return msg;
            }

        }.execute();

    }

    private void sendRegistrationIdToBackend() throws JSONException {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put(MessageType.KEY_MESSAGE_TYPE, MessageType.GCM_REGISTRATION);
        JSONObject jsonGcm = new JSONObject();
        TelephonyManager systemService = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
        String strDeviceId = systemService.getDeviceId();
        if (strDeviceId == null) {
            strDeviceId = KrakenUtils.getDeviceId(mContext);
        }
        jsonGcm.put("deviceID", hash(strDeviceId));
        jsonGcm.put("registrationToken", registrationToken);
        jsonObject.put("gcm", jsonGcm);

        ServerCommunication serverCommunication = new ServerCommunication(mContext, new GcmRegIdSentHandler());
        serverCommunication.postRequest(jsonObject);
    }

    /**
     * Stores the registration ID and app versionCode in the application's
     * {@code SharedPreferences}.
     */
    private void storeRegistrationId() {
        final SharedPreferences prefs = getGCMPreferences();
        int appVersion = getAppVersion(mContext);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(PROPERTY_REG_ID, registrationToken);
        editor.putInt(APP_VERSION, appVersion);
        editor.commit();
    }

    @SuppressLint("DefaultLocale")
    private String hash(String value) {
        String hash = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            byte[] bytes = value.getBytes("UTF-8");
            digest.update(bytes, 0, bytes.length);
            bytes = digest.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%02X", b));
            }
            hash = sb.toString().toLowerCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return hash;
    }

    private static class GcmRegIdSentHandler implements IServerCommunicationResponseHandler {
        @Override
        public void handleData(Bundle data) {
            if (data.containsKey("error") && data.getBoolean("error") == false) {
                getInstance().storeRegistrationId();
            }
        }
    }
}