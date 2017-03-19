package com.test.themobilebakerytest.user;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.Expose;

/**
 * Created by mmc on 17/3/17.
 */

@Table(name = "logins", id = "_id")
public class Login extends Model {

    @Expose
    @Column(name = "username")
    String username;
    @Expose
    @Column(name = "password")
    String password;

    public Login(){}

    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPasswordCovered() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < password.length(); i++) {
            builder.append("*");
        }
        return builder.toString();
    }

}
