package com.test.themobilebakerytest.user;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.Expose;

/**
 * Created by mmc on 16/3/17.
 */

@Table(name = "pictures", id = "_id")
public class Picture extends Model {

    @Expose
    @Column(name = "large")
    String large;
    @Expose
    @Column(name = "medium")
    String medium;
    @Expose
    @Column(name = "thumbnail")
    String thumbnail;

    public Picture(){}

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
