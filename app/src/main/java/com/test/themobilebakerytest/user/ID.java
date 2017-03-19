package com.test.themobilebakerytest.user;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.Expose;

/**
 * Created by mmc on 17/3/17.
 */

@Table(name = "ids", id = "_id")
public class ID extends Model {

    @Expose
    @Column(name = "name")
    String name;
    @Expose
    @Column(name = "value")
    String value;

    public ID(){}

    public ID(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
