package com.test.themobilebakerytest.main;

import com.test.themobilebakerytest.user.User;

import java.util.List;

/**
 * Created by mmc on 16/3/17.
 */

public class MainPresenterImpl implements MainPresenter, LoadUserListInteractor.OnFinishedListener {

    private MainView mainView;
    private LoadUserListInteractor loadUserListInteractor;

    public MainPresenterImpl(MainView mainView, LoadUserListInteractor loadUserListInteractor) {
        this.mainView = mainView;
        this.loadUserListInteractor = loadUserListInteractor;
    }

    @Override
    public void onResume() {
        if (mainView != null) {
            mainView.showProgress();
        }
        loadUserListInteractor.loadItems(this);
    }

    @Override
    public void onItemClicked(int position) {

    }

    @Override
    public void onDestroy() {
        mainView = null;
    }

    @Override
    public void onFinished(List<User> items) {
        if (mainView != null) {
            mainView.setItems(items);
            mainView.hideProgress();
        }
    }

    @Override
    public void onFinishedError(List<User> items, String error) {
        if (mainView != null) {
            onFinished(items);
            mainView.showError(error);
        }
    }
}
