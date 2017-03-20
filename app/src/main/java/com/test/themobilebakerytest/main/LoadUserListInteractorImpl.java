package com.test.themobilebakerytest.main;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.test.themobilebakerytest.R;
import com.test.themobilebakerytest.user.User;
import com.test.themobilebakerytest.user.UserList;
import com.test.themobilebakerytest.user.UserUtils;
import com.test.themobilebakerytest.utils.ApiService;
import com.test.themobilebakerytest.utils.RetroClient;
import com.test.themobilebakerytest.utils.TaskFinishedListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mmc on 16/3/17.
 */

public class LoadUserListInteractorImpl implements LoadUserListInteractor {

    private final String URL_USERS = "https://api.randomuser.me/?results=100&seed=themobilebakery";
    //private List<User> users = new ArrayList<>();

    @Override
    public void loadItems(final OnFinishedListener listener) {

        UserUtils.getUsersFromDatabase(new TaskFinishedListener<List<User>>() {
            @Override
            public void onSuccess(List<User> usersWithIds) {
                listener.onFinished(usersWithIds);
            }

            @Override
            public void onFailure(List<User> emptyOrNullList) {
                storeUsersFromWeb(listener);
            }
        });

    }

    @Override
    public void onDeleteUserClicked(Context context, final User user, final int position, final OnUserDeletedListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(R.string.Delete_this_user_);
        builder.setNegativeButton(android.R.string.cancel, null);
        builder.setPositiveButton(R.string.Delete, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                user.delete();
                listener.onUserDeleted(user, position);
            }
        });
        builder.show();
    }



    private void storeUsersFromWeb(final OnFinishedListener listener) {


        ApiService api = RetroClient.getApiService(RetroClient.ROOT_URL_RANDOMUSER);

        Call<UserList> userListCall = api.getUsersJson();

        userListCall.enqueue(new Callback<UserList>() {
            @Override
            public void onResponse(Call<UserList> call, Response<UserList> response) {
                List<User> usersWithoutSQLIds = new ArrayList<>();

                if (response.isSuccessful()) {
                    usersWithoutSQLIds = response.body().getUsers();
                }
                UserUtils.saveMultipleUsersToDatabase(usersWithoutSQLIds, new TaskFinishedListener<List<User>>() {
                    @Override
                    public void onSuccess(List<User> usersWithoutSQLIds) {

                        UserUtils.getUsersFromDatabase(new TaskFinishedListener<List<User>>() {
                            @Override
                            public void onSuccess(List<User> usersWithSQLIds) {
                                listener.onFinished(usersWithSQLIds);
                            }

                            @Override
                            public void onFailure(List<User> usersWithoutSQLIds) {
                                listener.onFinished(usersWithoutSQLIds);
                            }
                        });

                    }

                    @Override
                    public void onFailure(List<User> usersWithoutSQLIds) {
                        listener.onFinished(usersWithoutSQLIds);
                    }
                });
            }

            @Override
            public void onFailure(Call<UserList> call, Throwable t) {
                listener.onFinishedError(null, "Error");
            }
        });
    }

}
