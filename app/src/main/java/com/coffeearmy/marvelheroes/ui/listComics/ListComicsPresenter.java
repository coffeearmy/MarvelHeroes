package com.coffeearmy.marvelheroes.ui.listComics;

import android.util.Log;

import com.coffeearmy.marvelheroes.base.BasePresenter;
import com.coffeearmy.marvelheroes.model.Comic;
import com.coffeearmy.marvelheroes.model.ComicView;
import com.coffeearmy.marvelheroes.model.mapper.ComicDomainMapper;
import com.coffeearmy.marvelheroes.useCases.GetListComicsUseCase;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DefaultObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * :3
 */

public class ListComicsPresenter implements BasePresenter<ListComicsViewModel> {


    private static final String TAG = ListComicsPresenter.class.getSimpleName();


    GetListComicsUseCase getListComicsUseCase;

    private ComicDomainMapper comicDomainMapper;

    private ListComicsViewModel listComicsView;
    private int offset=0;

    @Inject
    public ListComicsPresenter(GetListComicsUseCase getListComicsUseCase, ComicDomainMapper comicDomainMapper) {
        this.getListComicsUseCase = getListComicsUseCase;
        this.comicDomainMapper = comicDomainMapper;
    }


    @Override
    public void attachView(ListComicsViewModel view) {
        listComicsView = view;
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onViewReady() {
        getComics();
    }

    @Override
    public void detachView() {

    }

    @Override
    public void pause() {

    }


    private void getComics() {
        listComicsView.showLoading();
        getListComicsUseCase.execute("1009415", offset)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<List<Comic>>() {
                    @Override
                    public void onNext(List<Comic> comics) {
                        Log.i(TAG, "onNext: "+comics.size());

                        offset=offset+comics.size();
                        showComic(comicDomainMapper.domainToView(comics));
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError: OHNO"+ Log.getStackTraceString(e));
                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG, "Complete");
                        listComicsView.hideLoading();
                    }
                });
    }

    private void showComic(List<ComicView> comics) {
        listComicsView.showComics(comics);
    }


    void loadMoreItems() {
         getComics();
    }

    boolean needsMoreItems(int firstVisibleItemPosition, int visibleItems, int totalItems) {
        return !(firstVisibleItemPosition+visibleItems<totalItems);
    }
}
