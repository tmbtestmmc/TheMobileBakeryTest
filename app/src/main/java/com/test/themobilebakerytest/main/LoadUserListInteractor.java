package com.test.themobilebakerytest.main;

import android.content.Context;

import com.test.themobilebakerytest.user.User;

import java.util.List;

/**
 * Created by mmc on 16/3/17.
 */

public interface LoadUserListInteractor {

    interface OnFinishedListener {
        void onFinished(List<User> items);
        void onFinishedError(List<User> items, String error);
    }

    void loadItems(OnFinishedListener listener);

    void onDeleteUserClicked(Context context, User user, int position, OnUserDeletedListener onUserDeletedListener);

    interface OnUserDeletedListener {
        void onUserDeleted(User user, int position);
    }


}
