package com.test.themobilebakerytest.user;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mmc on 17/3/17.
 */

public class UserUtils {

    private static final String LOG_TAG = UserUtils.class.getSimpleName();

    public static List<User> extractUsersFromJSON(String json) {
        ArrayList<User> users = new ArrayList<>();

        if (!TextUtils.isEmpty(json)) {
            try {
                JSONObject strJson = new JSONObject(json);
                JSONArray resultsArray = strJson.getJSONArray("results");
                Gson gson = new Gson();

                for (int i = 0; i < resultsArray.length(); i++) {
                    JSONObject user = resultsArray.getJSONObject(i);
                    users.add(gson.fromJson(user.toString(), User.class));
                }

            } catch (JSONException e) {
                Log.e(LOG_TAG, "Problem parsing the JSON results", e);
            }
        }
        return users;
    }

    public static Double[] extractLatAndLongFromJSON(String json) {
        Double[] latLng = new Double[2];

        if (!TextUtils.isEmpty(json)) {
            try {
                JSONObject strJson = new JSONObject(json);
                JSONArray resultsArray = strJson.getJSONArray("results");
                JSONObject result = resultsArray.getJSONObject(0);
                JSONObject geometry = result.getJSONObject("geometry");
                JSONObject location = geometry.getJSONObject("location");
                latLng[0] = location.getDouble("lat");
                latLng[1] = location.getDouble("lng");
            } catch (JSONException e) {
                Log.e(LOG_TAG, "Problem parsing the JSON results", e);
            }
        }
        return latLng;
    }

}
