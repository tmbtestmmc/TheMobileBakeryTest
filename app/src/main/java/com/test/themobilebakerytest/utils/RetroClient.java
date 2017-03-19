package com.test.themobilebakerytest.utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mmc on 19/3/17.
 */

public class RetroClient {

    public static final String ROOT_URL_RANDOMUSER = "https://api.randomuser.me";
    public static final String ROOT_URL_COORDINATES = "https://maps.googleapis.com";

    private static Retrofit getRetrofitInstance(String rootUrl) {
        return new Retrofit.Builder()
                .baseUrl(rootUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ApiService getApiService(String rootUrl) {
        return getRetrofitInstance(rootUrl).create(ApiService.class);
    }
}
