package com.test.themobilebakerytest.details;

import android.os.AsyncTask;
import android.text.TextUtils;

import com.test.themobilebakerytest.utils.Utils;
import com.test.themobilebakerytest.user.User;
import com.test.themobilebakerytest.user.UserUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;

import static com.test.themobilebakerytest.utils.Utils.GOOGLE_MAP_API_KEY;

/**
 * Created by mmc on 16/3/17.
 */

public class LoadDetailsInteractorImpl implements LoadDetailsInteractor {

    private User user;
    private final String URL_START = "https://maps.googleapis.com/maps/api/geocode/json?address=";
    private final String URL_KEY = "&key=";

    LoadDetailsInteractorImpl(User user) {
        this.user = user;
    }

    @Override
    public void loadDetails(final OnFinishedListener listener) {
        new AsyncTask<User, Void, Double[]>() {
            @Override
            protected Double[] doInBackground(User... users) {

                Double[] coords = null;
                URL urlFullAddress = Utils.createUrl(URL_START + users[0].getLocation().getUrlLocation() + URL_KEY + GOOGLE_MAP_API_KEY);

                String jsonCoord = null;
                jsonCoord = Utils.makeHttpRequest(urlFullAddress);

                // La mayor parte de las direcciones random no existen, por lo que si se da el caso buscamos las coordenadas únicamente de la ciudad.
                if (!TextUtils.isEmpty(jsonCoord)) {
                    try {
                        JSONObject jsonObject = new JSONObject(jsonCoord);
                        String status = jsonObject.optString("status");
                        if (status != null && status.equals("ZERO_RESULTS")) {
                            URL urlCity = Utils.createUrl(URL_START + users[0].getLocation().getUrlCity() + URL_KEY + GOOGLE_MAP_API_KEY);
                            jsonCoord = Utils.makeHttpRequest(urlCity);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                if (!TextUtils.isEmpty(jsonCoord)) {
                    coords = UserUtils.extractLatAndLongFromJSON(jsonCoord);
                }

                return coords;
            }

            @Override
            protected void onPostExecute(Double[] coords) {
                listener.onFinished(coords);
            }
        }.execute(user);
    }


}
