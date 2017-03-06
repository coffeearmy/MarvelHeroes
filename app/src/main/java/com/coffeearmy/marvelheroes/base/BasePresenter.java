package com.coffeearmy.marvelheroes.base;

/**
 * :3
 */

public interface BasePresenter<T extends BaseViewModel> {


    public void initialize(T view);

    void onPause();
}
