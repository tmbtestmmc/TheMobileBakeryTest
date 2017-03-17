package com.test.themobilebakerytest.user;

import android.content.Context;
import android.content.res.Resources;

import com.test.themobilebakerytest.R;

/**
 * Created by mmc on 16/3/17.
 */

public class User {

    String gender;
    Name name;
    Location location;
    String email;
    Login login;
    String phone;
    String cell;
    ID id;
    Picture picture;
    String nat;

    public User(String gender, Name name, Location location, String email, Login login, String phone, String cell, ID id, Picture picture, String nat) {
        this.gender = gender;
        this.name = name;
        this.location = location;
        this.email = email;
        this.login = login;
        this.phone = phone;
        this.cell = cell;
        this.id = id;
        this.picture = picture;
        this.nat = nat;
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

    public ID getId() {
        return id;
    }

    public Picture getPicture() {
        return picture;
    }

    public String getNationality() {
        return nat;
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
        sb.append(T).append(r.getString(R.string.Name)).append(COLON).append(getId().getName()).append(BR);
        sb.append(T).append(r.getString(R.string.Value)).append(COLON).append(getId().getValue()).append(BR_BR);
        sb.append(r.getString(R.string.Nationality)).append(COLON).append(getNationality());
        return sb.toString();
    }
}
