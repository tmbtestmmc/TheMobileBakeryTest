package com.test.themobilebakerytest.details;

import com.google.gson.JsonObject;
import com.test.themobilebakerytest.user.User;
import com.test.themobilebakerytest.user.UserUtils;
import com.test.themobilebakerytest.utils.ApiService;
import com.test.themobilebakerytest.utils.RetroClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.test.themobilebakerytest.utils.Utils.GOOGLE_MAP_API_KEY;

/**
 * Created by mmc on 16/3/17.
 */

public class LoadDetailsInteractorImpl implements LoadDetailsInteractor {

    private ApiService apiService;
    private User user;
    private OnFinishedListener listener;
    private int locationsIndex = 0;
    private String[] locationsFromMoreToLessSpecific;


    LoadDetailsInteractorImpl() {
    }

    @Override
    public void loadCoordinates(final String jsonString, final OnFinishedListener listener) {

        user = UserUtils.extractUserFromJSON(jsonString);
        this.listener = listener;

        if (user != null) {

            apiService = RetroClient.getApiService(RetroClient.ROOT_URL_COORDINATES);

            locationsFromMoreToLessSpecific = new String[]{
                    user.getLocation().getFullLocation(),
                    user.getLocation().getCity()
            };

            makeCallToGetCoordinates();
        }

    }

    private void makeCallToGetCoordinates() {
        String location = locationsFromMoreToLessSpecific[locationsIndex];
        Call<JsonObject> stringCall = apiService.getCoordinatesJson(location, GOOGLE_MAP_API_KEY);

        stringCall.enqueue(new Callback<JsonObject>() {

            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    JsonObject jsonCoords = response.body();

                    if (addressFound(jsonCoords)) {
                        setCoordinatesToUser(jsonCoords, user);
                        listener.onFinished(user);
                    } else {
                        locationsIndex++;
                        if (lessSpecificAddressesAreAvailable()) {
                            makeCallToGetCoordinates();
                        } else {
                            listener.onFinished(user);
                        }
                    }

                } else {
                    listener.onFinished(user);
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                listener.onFinished(user);
            }
        });
    }

    private boolean lessSpecificAddressesAreAvailable() {
        return locationsIndex < locationsFromMoreToLessSpecific.length;
    }

    private void setCoordinatesToUser(JsonObject coordinates, User user) {
        Double[] coords = UserUtils.extractLatAndLongFromJSON(coordinates.toString());
        user.getLocation().setLatitude(coords[0]);
        user.getLocation().setLongitude(coords[1]);
    }

    private boolean addressFound(JsonObject jsonCoordinates) {
        String status = jsonCoordinates.get("status").getAsString();
        return status != null && status.equals("OK");
    }

}
