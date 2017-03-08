package com.coffeearmy.marvelheroes.base;

/**
 * :3
 */

public interface BasePresenter<T extends BaseViewModel> {


    public void attachView(T view);

    void onPause();

    void onViewReady();

    void detachView();

    void pause();
}
