package com.test.themobilebakerytest.main;

import android.content.Context;

import com.test.themobilebakerytest.user.User;

/**
 * Created by mmc on 16/3/17.
 */

public interface MainPresenter {

    void onResume();

    void onItemClicked(int position);

    void onDeleteClick(Context context, User user, int position);

    void onUserDeleted(User user, int position);

    void onDestroy();

}
