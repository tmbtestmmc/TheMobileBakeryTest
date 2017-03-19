package com.test.themobilebakerytest.details;

import com.test.themobilebakerytest.user.User;

/**
 * Created by mmc on 16/3/17.
 */

public class DetailsPresenterImpl implements DetailsPresenter, LoadDetailsInteractor.OnFinishedListener {

    private DetailsView detailsView;
    private LoadDetailsInteractor loadDetailsInteractor;

    public DetailsPresenterImpl(DetailsView detailsView, LoadDetailsInteractor loadDetailsInteractor) {
        this.detailsView = detailsView;
        this.loadDetailsInteractor = loadDetailsInteractor;
    }

    @Override
    public void onCreate(String jsonString) {
        if (detailsView != null) {
            detailsView.showProgress();
        }
        loadDetailsInteractor.loadCoordinates(jsonString, this);
    }

    @Override
    public void onDestroy() {
        detailsView = null;
    }

    @Override
    public void onUserLoaded(User user) {
        if (detailsView != null) {
            detailsView.hideProgress();
            detailsView.onUserLoaded(user);
        }
    }

    @Override
    public void onCoordinatesLoaded(User user) {
        if (detailsView != null) {
            detailsView.onCoordinatesLoaded(user);
            detailsView.hideProgress();
        }
    }
}
