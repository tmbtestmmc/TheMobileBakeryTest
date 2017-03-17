package com.test.themobilebakerytest.user;

/**
 * Created by mmc on 16/3/17.
 */

public class Name {

    String title;
    String first;
    String last;

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
