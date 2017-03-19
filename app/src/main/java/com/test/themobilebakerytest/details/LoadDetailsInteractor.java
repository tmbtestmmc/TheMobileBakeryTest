package com.test.themobilebakerytest.details;

import com.test.themobilebakerytest.user.User;

/**
 * Created by mmc on 16/3/17.
 */

public interface LoadDetailsInteractor {

    interface OnFinishedListener {
        void onUserLoaded(User user);
        void onCoordinatesLoaded(User user);
    }

    void loadCoordinates(String jsonString, OnFinishedListener listener);

}
