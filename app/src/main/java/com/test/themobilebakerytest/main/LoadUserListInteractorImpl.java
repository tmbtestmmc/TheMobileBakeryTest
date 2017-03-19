package com.test.themobilebakerytest.main;

import android.os.AsyncTask;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.test.themobilebakerytest.user.User;
import com.test.themobilebakerytest.user.UserUtils;
import com.test.themobilebakerytest.utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mmc on 16/3/17.
 */

public class LoadUserListInteractorImpl implements LoadUserListInteractor {

    private final String URL_USERS = "https://api.randomuser.me/?results=100&seed=themobilebakery";

    @Override
    public void loadItems(final OnFinishedListener listener) {
        new AsyncTask<Void, Void, List<User>>() {
            @Override
            protected List<User> doInBackground(Void... params) {

                List<User> users = UserUtils.getUsersFromDatabase();

                if (users == null || users.size() == 0) {
                    URL url = Utils.createUrl(URL_USERS);

                    String jsonUsers = Utils.makeHttpRequest(url);

                    if (jsonUsers == null) {
                        cancel(true);
                    }

                    if (!TextUtils.isEmpty(jsonUsers)) {
                        try {
                            JSONArray array = new JSONObject(jsonUsers).getJSONArray("results");
                            users = new ArrayList<>();
                            Gson gson = new Gson();
                            for (int i = 0; i < array.length(); i++) {
                                String jsonUserTemp = array.getJSONObject(i).toString();
                                User userTemp = gson.fromJson(jsonUserTemp, User.class);
                                users.add(userTemp);
                            }
                            UserUtils.saveMultipleUsersToDatabase(users);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

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
