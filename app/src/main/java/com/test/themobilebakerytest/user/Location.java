package com.test.themobilebakerytest.user;

/**
 * Created by mmc on 16/3/17.
 */

public class Location {

    String street;
    String city;
    String state;
    String postcode;

    public Location(String street, String city, String state, String postcode) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.postcode = postcode;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getFullLocation() {
        final String separator = ", ";
        return street + separator + city + separator + state + separator + postcode;
    }

    public String getUrlLocation() {
        String finalString = getFullLocation().trim().replaceAll(" +", " ");
        finalString = finalString.replaceAll(" ", "+");
        return finalString;
    }

    public String getUrlCity() {
        String finalString = getCity().trim().replaceAll(" +", " ");
        finalString = finalString.replaceAll(" ", "+");
        return finalString;
    }
}
