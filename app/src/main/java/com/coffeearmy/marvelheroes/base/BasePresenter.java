package com.coffeearmy.marvelheroes.base;

/**
 *
 */

public interface BasePresenter<T extends BaseViewModel> {


    void attachView(T view);

    void onViewReady();

    void detachView();

    void pause();
}
