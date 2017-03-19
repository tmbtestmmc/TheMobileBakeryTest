package com.test.themobilebakerytest.user;

import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.Select;
import com.google.gson.Gson;
import com.test.themobilebakerytest.utils.TaskFinishedListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mmc on 17/3/17.
 */

public class UserUtils {

    private static final String LOG_TAG = UserUtils.class.getSimpleName();

    public static User extractUserFromJSON(String json) {

        User user = null;

        if (!TextUtils.isEmpty(json)) {
            Gson gson = new Gson();
            user = gson.fromJson(json, User.class);
        }
        return user;
    }

    public static Double[] extractLatAndLongFromJSON(String json) {
        Double[] latLng = new Double[2];

        if (!TextUtils.isEmpty(json)) {
            try {
                JSONObject strJson = new JSONObject(json);
                JSONArray resultsArray = strJson.getJSONArray("results");
                JSONObject result = resultsArray.getJSONObject(0);
                JSONObject geometry = result.getJSONObject("geometry");
                JSONObject location = geometry.getJSONObject("location");
                latLng[0] = location.getDouble("lat");
                latLng[1] = location.getDouble("lng");
            } catch (JSONException e) {
                Log.e(LOG_TAG, "Problem parsing the JSON results", e);
            }
        }
        return latLng;
    }

    public static void getUsersFromDatabase(final TaskFinishedListener<List<User>> onTaskFinishedListener) {
        new AsyncTask<Void, Void, List<User>>() {
            @Override
            protected List<User> doInBackground(Void... params) {
                List<User> users = new ArrayList<>();
                try {
                    users = new Select()
                            .from(User.class)
                            .execute();
                } catch (Exception e) {

                }
                if (users == null || users.size() == 0) {
                    cancel(true);
                }
                return users;
            }

            @Override
            protected void onPostExecute(List<User> users) {
                if (onTaskFinishedListener != null) {
                    onTaskFinishedListener.onSuccess(users);
                }
            }

            @Override
            protected void onCancelled(List<User> users) {
                if (onTaskFinishedListener != null) {
                    onTaskFinishedListener.onFailure(users);
                }
            }
        }.execute();
    }

    public static User saveUserToDatabase(User user) {
        if (user != null) {
            Name name = new Name(user.getName().getTitle(), user.getName().getFirst(), user.getName().getLast());
            Location location = new Location(user.getLocation().getStreet(), user.getLocation().getCity(), user.getLocation().getState(), user.getLocation().getPostcode());
            Login login = new Login(user.getLogin().getUsername(), user.getLogin().getPassword());
            ID id = new ID(user.getUserID().getName(), user.getUserID().getValue());
            Picture picture = new Picture(user.getPicture().getLarge(), user.getPicture().getMedium(), user.getPicture().getLarge());
            name.save();
            location.save();
            login.save();
            id.save();
            picture.save();

            User userTemp = new User(user.getGender(), name, location, user.getEmail(), login, user.getPhone(), user.getCell(), id, picture, user.getNationality());
            userTemp.save();
        }
        return user;
    }

    public static void saveMultipleUsersToDatabase(final List<User> users, final TaskFinishedListener<List<User>> taskFinishedListener) {

        new AsyncTask<Void, Void, List<User>>() {
            @Override
            protected List<User> doInBackground(Void... params) {
                ActiveAndroid.beginTransaction();
                try {
                    for (User user : users) {
                        saveUserToDatabase(user);
                    }
                    ActiveAndroid.setTransactionSuccessful();
                } catch (Exception e) {
                    cancel(true);
                } finally {
                    ActiveAndroid.endTransaction();
                }
                return users;
            }

            @Override
            protected void onPostExecute(List<User> users) {
                if (taskFinishedListener != null) {
                    taskFinishedListener.onSuccess(users);
                }
            }

            @Override
            protected void onCancelled(List<User> users) {
                if (taskFinishedListener != null) {
                    taskFinishedListener.onFailure(users);
                }
            }
        }.execute();


    }

}
