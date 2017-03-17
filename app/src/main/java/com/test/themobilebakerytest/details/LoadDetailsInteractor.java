package com.test.themobilebakerytest.details;

/**
 * Created by mmc on 16/3/17.
 */

public interface LoadDetailsInteractor {

    interface OnFinishedListener {
        void onFinished(Double[] coordinates);
    }

    void loadDetails(OnFinishedListener listener);

}
