package com.test.themobilebakerytest.details;

import android.os.AsyncTask;
import android.text.TextUtils;

import com.test.themobilebakerytest.user.User;
import com.test.themobilebakerytest.user.UserUtils;
import com.test.themobilebakerytest.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;

import static com.test.themobilebakerytest.utils.Utils.GOOGLE_MAP_API_KEY;

/**
 * Created by mmc on 16/3/17.
 */

public class LoadDetailsInteractorImpl implements LoadDetailsInteractor {

    private final String URL_START = "https://maps.googleapis.com/maps/api/geocode/json?address=";
    private final String URL_KEY = "&key=";

    LoadDetailsInteractorImpl() {
    }

    @Override
    public void loadCoordinates(final String jsonString, final OnFinishedListener listener) {
        new AsyncTask<String, Void, User>() {
            @Override
            protected User doInBackground(String... jsonStrings) {

                User user = UserUtils.extractUserFromJSON(jsonStrings[0]);
                Double[] coords = new Double[2];
                URL urlFullAddress = Utils.createUrl(URL_START + user.getLocation().getUrlLocation() + URL_KEY + GOOGLE_MAP_API_KEY);

                String jsonCoord = null;
                jsonCoord = Utils.makeHttpRequest(urlFullAddress);

                if (!TextUtils.isEmpty(jsonCoord)) {
                    try {
                        JSONObject jsonObject = new JSONObject(jsonCoord);
                        String status = jsonObject.optString("status");
                        if (status != null && status.equals("ZERO_RESULTS")) {
                            URL urlCity = Utils.createUrl(URL_START + user.getLocation().getUrlCity() + URL_KEY + GOOGLE_MAP_API_KEY);
                            jsonCoord = Utils.makeHttpRequest(urlCity);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                if (!TextUtils.isEmpty(jsonCoord)) {
                    coords = UserUtils.extractLatAndLongFromJSON(jsonCoord);
                }

                user.getLocation().setLatitude(coords[0]);
                user.getLocation().setLongitude(coords[1]);

                return user;
            }

            @Override
            protected void onPostExecute(User user) {
                listener.onFinished(user);
            }
        }.execute(jsonString);
    }


}
