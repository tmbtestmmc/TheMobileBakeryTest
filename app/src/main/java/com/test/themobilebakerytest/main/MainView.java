package com.test.themobilebakerytest.main;

import com.test.themobilebakerytest.user.User;

import java.util.List;

/**
 * Created by mmc on 16/3/17.
 */

public interface MainView {

    void showProgress();

    void hideProgress();

    void setItems(List<User> users);

    void showError(String error);

}
