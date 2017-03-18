package com.test.themobilebakerytest.main;

import android.os.AsyncTask;
import android.text.TextUtils;

import com.test.themobilebakerytest.utils.Utils;
import com.test.themobilebakerytest.user.User;
import com.test.themobilebakerytest.user.UserUtils;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mmc on 16/3/17.
 */

public class LoadItemsInteractorImpl implements LoadItemsInteractor {

    private final String URL_USERS = "https://api.randomuser.me/?results=100&seed=themobilebakery";

    @Override
    public void loadItems(final OnFinishedListener listener) {
        new AsyncTask<Void, Void, List<User>>() {
            @Override
            protected List<User> doInBackground(Void... params) {

                List<User> users = new ArrayList<>();
                URL url = Utils.createUrl(URL_USERS);

                String jsonUsers = Utils.makeHttpRequest(url);

                if (jsonUsers == null) {
                    cancel(true);
                }

                if (!TextUtils.isEmpty(jsonUsers)) {
                    users = UserUtils.extractUsersFromJSON(jsonUsers);
                }
                return users;
            }

            @Override
            protected void onPostExecute(List<User> users) {
                listener.onFinished(users);
            }

            @Override
            protected void onCancelled(List<User> users) {
                listener.onFinishedError(users, "Error");
            }
        }.execute();
    }

}
