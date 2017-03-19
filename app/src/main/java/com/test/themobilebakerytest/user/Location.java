package com.test.themobilebakerytest.user;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.Expose;

/**
 * Created by mmc on 16/3/17.
 */

@Table(name = "locations", id = "_id")
public class Location extends Model {

    @Expose
    @Column(name = "street")
    String street;
    @Expose
    @Column(name = "city")
    String city;
    @Expose
    @Column(name = "state")
    String state;
    @Expose
    @Column(name = "postcode")
    String postcode;

    Double latitude;
    Double longitude;

    public Location(){}

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

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
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
