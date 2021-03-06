package de.tudarmstadt.informatik.tk.assistance.sdk.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * @author Wladimir Schmidt (wlsc.dev@gmail.com)
 * @date 17.10.2015
 */
public enum AppUtils {
    ;

    /**
     * Returns true if debuggable flag is enabled
     *
     * @param context
     * @return
     */
    public static boolean isDebug(Context context) {
        return 0 != (context.getApplicationInfo().flags &= ApplicationInfo.FLAG_DEBUGGABLE);
    }

    /**
     * Generates new random UUID string
     *
     * @return
     */
    public static String generateUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * Generates SHA256 hash in HEX of a given string
     *
     * @param someString
     * @return
     */
    public static String generateSHA256(String someString) {

        MessageDigest md;
        String result = "";

        try {
            md = MessageDigest.getInstance("SHA-256");

            md.update(someString.getBytes());

            byte[] bytes = md.digest();

            result = convertBytesToString(bytes);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * Converts bytes to string
     *
     * @param bytes
     * @return
     */
    public static String convertBytesToString(byte... bytes) {

        StringBuilder sb = new StringBuilder();

        for (byte aByte : bytes) {
            sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }

    /**
     * Opens an APP by package name
     *
     * @param context
     * @param packageName
     * @return
     */
    public static boolean openApp(Context context, String packageName) {

        PackageManager packageManager = context.getPackageManager();

        try {
            Intent intent = packageManager.getLaunchIntentForPackage(packageName);

            if (intent == null) {
                return false;
            }

            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            context.startActivity(intent);

            return true;

        } catch (RuntimeException e) {
            return false;
        }
    }
}