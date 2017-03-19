package com.test.themobilebakerytest.utils;

/**
 * Created by mmc on 19/3/17.
 */

public interface TaskFinishedListener<T extends Object> {
    void onSuccess(T object);
    void onFailure(T object);
}
