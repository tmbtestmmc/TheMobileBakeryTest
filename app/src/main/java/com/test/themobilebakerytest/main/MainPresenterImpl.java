package com.test.themobilebakerytest.main;

import com.test.themobilebakerytest.user.User;

import java.util.List;

/**
 * Created by mmc on 16/3/17.
 */

public class MainPresenterImpl implements MainPresenter, LoadItemsInteractor.OnFinishedListener {

    private MainView mainView;
    private LoadItemsInteractor loadItemsInteractor;

    public MainPresenterImpl(MainView mainView, LoadItemsInteractor loadItemsInteractor) {
        this.mainView = mainView;
        this.loadItemsInteractor = loadItemsInteractor;
    }

    @Override
    public void onResume() {
        if (mainView != null) {
            mainView.showProgress();
        }
        loadItemsInteractor.loadItems(this);
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
