package com.test.themobilebakerytest.details;

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
    public void onResume() {
        if (detailsView != null) {
            detailsView.showProgress();
        }
        loadDetailsInteractor.loadDetails(this);
    }

    @Override
    public void onDestroy() {
        detailsView = null;
    }

    @Override
    public void onFinished(Double[] coordinates) {
        if (detailsView != null) {
            detailsView.setCoordinates(coordinates);
            detailsView.hideProgress();
        }
    }
}
