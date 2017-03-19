package com.test.themobilebakerytest.user;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.Expose;

/**
 * Created by mmc on 16/3/17.
 */

@Table(name = "names", id = "_id")
public class Name extends Model {

    @Expose
    @Column(name = "title")
    String title;
    @Expose
    @Column(name = "first")
    String first;
    @Expose
    @Column(name = "last")
    String last;

    public Name(){}

    public Name(String title, String first, String last) {
        this.title = title;
        this.first = first;
        this.last = last;
    }

    public String getTitle() {
        return title;
    }

    public String getFirst() {
        return first;
    }

    public String getLast() {
        return last;
    }

    public String toString() {
        return title + " " + first + " " + last;
    }

}
