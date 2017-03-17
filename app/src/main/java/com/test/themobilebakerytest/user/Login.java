package com.test.themobilebakerytest.user;

/**
 * Created by mmc on 17/3/17.
 */

public class Login {
    String username;
    String password;

    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswordCovered() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < password.length(); i++) {
            builder.append("*");
        }
        return builder.toString();
    }

}
