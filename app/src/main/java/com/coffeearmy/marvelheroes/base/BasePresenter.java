package com.coffeearmy.marvelheroes.base;

/**
 * :3
 */

public interface BasePresenter<T extends BaseViewModel> {


    void attachView(T view);

    void onViewReady();

    void detachView();

    void pause();
}
