package com.test.themobilebakerytest.main;

import com.test.themobilebakerytest.user.User;

import java.util.List;

/**
 * Created by mmc on 16/3/17.
 */

public interface LoadItemsInteractor {

    interface OnFinishedListener {
        void onFinished(List<User> items);
        void onFinishedError(List<User> items, String error);
    }

    void loadItems(OnFinishedListener listener);

}
