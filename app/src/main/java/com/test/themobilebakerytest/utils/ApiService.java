package com.test.themobilebakerytest.utils;

import com.google.gson.JsonObject;
import com.test.themobilebakerytest.user.UserList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by mmc on 19/3/17.
 */

public interface ApiService {

    @GET("?results=100&seed=themobilebakery")
    Call<UserList> getUsersJson();

    @GET("maps/api/geocode/json?")
    Call<JsonObject> getCoordinatesJson(@Query("address") String address, @Query("key") String key);

}