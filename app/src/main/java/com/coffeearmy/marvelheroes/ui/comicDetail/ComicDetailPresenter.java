package com.coffeearmy.marvelheroes.ui.comicDetail;

import com.coffeearmy.marvelheroes.base.BasePresenter;
import com.coffeearmy.marvelheroes.base.BaseViewModel;
import com.coffeearmy.marvelheroes.injector.ActivityScope;

import javax.inject.Inject;

/**
 * :3
 */
@ActivityScope
public class ComicDetailPresenter implements BasePresenter<ComicDetailViewModel> {

    @Inject
    public ComicDetailPresenter() {
    }

    @Override
    public void attachView(ComicDetailViewModel view) {}

    @Override
    public void onViewReady() { }

    @Override
    public void detachView() { }

    @Override
    public void pause() {  }
}
