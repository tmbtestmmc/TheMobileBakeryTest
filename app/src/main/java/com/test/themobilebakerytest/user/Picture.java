package com.test.themobilebakerytest.user;

/**
 * Created by mmc on 16/3/17.
 */

public class Picture {

    String large;
    String medium;
    String thumbnail;

    public Picture(String large, String medium, String thumbnail) {
        this.large = large;
        this.medium = medium;
        this.thumbnail = thumbnail;
    }

    public String getLarge() {
        return large;
    }

    public String getMedium() {
        return medium;
    }

    public String getThumbnail() {
        return thumbnail;
    }
}
