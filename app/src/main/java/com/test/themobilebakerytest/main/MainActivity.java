package com.test.themobilebakerytest.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.test.themobilebakerytest.R;
import com.test.themobilebakerytest.details.DetailsActivity;
import com.test.themobilebakerytest.user.User;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView, UserListAdapter.UserListItemClick {

    RecyclerView rvUserList;
    ProgressBar progressBar;
    RecyclerView.LayoutManager layoutManager;
    UserListAdapter adapter;
    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvUserList = (RecyclerView) findViewById(R.id.rvUserList);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        layoutManager = new LinearLayoutManager(this);
        rvUserList.setLayoutManager(layoutManager);
        rvUserList.setItemAnimator(new DefaultItemAnimator());
        presenter = new MainPresenterImpl(this, new LoadUserListInteractorImpl());
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        rvUserList.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
        rvUserList.setVisibility(View.VISIBLE);
    }

    @Override
    public void setItems(List<User> users) {
        adapter = new UserListAdapter(this, users, this);
        rvUserList.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (adapter == null) {
            presenter.onResume();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void onUserClick(int position) {
        //presenter.onItemClicked(position);
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra("user", gson.toJson(adapter.getItem(position), User.class));
        startActivity(intent);
    }
}
