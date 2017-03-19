package com.test.themobilebakerytest.details;

import com.test.themobilebakerytest.user.User;

/**
 * Created by mmc on 16/3/17.
 */

public interface DetailsView {
    void showProgress();

    void hideProgress();

    void userLoaded(User user);
}
