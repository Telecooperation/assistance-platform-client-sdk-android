package de.tudarmstadt.informatik.tk.assistance.sdk.util;

import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import org.json.JSONObject;


/**
 * @author Wladimir Schmidt (wlsc.dev@gmail.com)
 * @date 17.12.2015
 */
public enum JsonUtils {
    ;

    private static final Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

    public static Gson getGson() {
        return gson;
    }

    /**
     * Checks for valid JSON string with GSON library
     *
     * @param json
     * @return
     */
    public static boolean isValidJSON(String json) {

        try {
            gson.fromJson(json, Object.class);
            return true;
        } catch (JsonSyntaxException e) {
            return false;
        }
    }

    /**
     * Convert from org.JSON to com.google.json format
     *
     * @param object
     * @return
     */
    @Nullable
    public static JsonElement convert(JSONObject object) {

        if (object == null) {
            return null;
        }

        JsonParser parser = new JsonParser();
        JsonElement jsonElement = parser.parse(object.toString());

        return jsonElement;
    }
}