package com.test.themobilebakerytest.user;

import android.content.Context;
import android.content.res.Resources;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.test.themobilebakerytest.R;

/**
 * Created by mmc on 16/3/17.
 */

@Table(name = "users", id = "_id")
public class User extends Model {

    @Expose
    @Column(name = "gender")
    String gender;
    @Expose
    @Column(name = "name")
    Name name;
    @Expose
    @Column(name = "location")
    Location location;
    @Expose
    @Column(name = "email")
    String email;
    @Expose
    @Column(name = "login")
    Login login;
    @Expose
    @Column(name = "phone")
    String phone;
    @Expose
    @Column(name = "cell")
    String cell;
    @SerializedName("id")
    @Expose
    @Column(name = "userID")
    ID userID;
    @Expose
    @Column(name = "picture")
    Picture picture;
    @SerializedName("nat")
    @Expose
    @Column(name = "nationality")
    String nationality;

    public User(){}

    public User(String gender, Name name, Location location, String email, Login login, String phone, String cell, ID id, Picture picture, String nationality) {
        this.gender = gender;
        this.name = name;
        this.location = location;
        this.email = email;
        this.login = login;
        this.phone = phone;
        this.cell = cell;
        this.userID = id;
        this.picture = picture;
        this.nationality = nationality;
    }

    public String getGender() {
        return gender;
    }

    public Name getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public String getEmail() {
        return email;
    }

    public Login getLogin() {
        return login;
    }

    public String getPhone() {
        return phone;
    }

    public String getCell() {
        return cell;
    }

    public ID getUserID() {
        return userID;
    }

    public Picture getPicture() {
        return picture;
    }

    public String getNationality() {
        return nationality;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String toString(Context context) {
        final String COLON = ": ";
        final String BR = "\n";
        final String BR_BR = BR + BR;
        final String T = "\t";
        Resources r = context.getResources();
        StringBuilder sb = new StringBuilder();
        sb.append(r.getString(R.string.Gender)).append(COLON).append(getGender()).append(BR_BR);
        sb.append(r.getString(R.string.Name)).append(COLON).append(getName().toString()).append(BR_BR);
        sb.append(r.getString(R.string.Location)).append(COLON).append(getLocation().getFullLocation()).append(BR_BR);
        sb.append(r.getString(R.string.Email)).append(COLON).append(getEmail()).append(BR_BR);
        sb.append(r.getString(R.string.Login)).append(COLON).append(BR);
        sb.append(T).append(r.getString(R.string.Username)).append(COLON).append(getLogin().getUsername()).append(BR);
        sb.append(T).append(r.getString(R.string.Password)).append(COLON).append(getLogin().getPasswordCovered()).append(BR_BR);
        sb.append(r.getString(R.string.Phone)).append(COLON).append(getPhone()).append(BR_BR);
        sb.append(r.getString(R.string.Cell)).append(COLON).append(getCell()).append(BR_BR);
        sb.append(r.getString(R.string.ID)).append(COLON).append(BR);
        sb.append(T).append(r.getString(R.string.Name)).append(COLON).append(getUserID().getName()).append(BR);
        sb.append(T).append(r.getString(R.string.Value)).append(COLON).append(getUserID().getValue()).append(BR_BR);
        sb.append(r.getString(R.string.Nationality)).append(COLON).append(getNationality());
        return sb.toString();
    }
}
