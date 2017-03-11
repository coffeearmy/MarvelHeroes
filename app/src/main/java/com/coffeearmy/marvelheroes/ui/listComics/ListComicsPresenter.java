package com.coffeearmy.marvelheroes.ui.listComics;

import android.util.Log;

import com.coffeearmy.marvelheroes.base.BasePresenter;
import com.coffeearmy.marvelheroes.injector.ActivityScope;
import com.coffeearmy.marvelheroes.model.Comic;
import com.coffeearmy.marvelheroes.model.ComicView;
import com.coffeearmy.marvelheroes.model.mapper.ComicDomainMapper;
import com.coffeearmy.marvelheroes.useCases.GetListComicsUseCase;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DefaultObserver;
import io.reactivex.schedulers.Schedulers;

/**
 *
 */
@ActivityScope
public class ListComicsPresenter implements BasePresenter<ListComicsViewModel> {


    private static final String TAG = ListComicsPresenter.class.getSimpleName();


    GetListComicsUseCase getListComicsUseCase;

    private ComicDomainMapper comicDomainMapper;

    private ListComicsViewModel listComicsView;
    private int offset=0;
    private String  idCharacter;

    @Inject
    public ListComicsPresenter(GetListComicsUseCase getListComicsUseCase, ComicDomainMapper comicDomainMapper, String idCharacter) {
        this.getListComicsUseCase = getListComicsUseCase;
        this.comicDomainMapper = comicDomainMapper;
        this.idCharacter = idCharacter;
    }


    @Override
    public void attachView(ListComicsViewModel view) {
        listComicsView = view;
    }


    @Override
    public void onViewReady() {
        getComics();
    }

    @Override
    public void detachView() {
        listComicsView=null;
    }

    @Override
    public void pause() {}


    protected void getComics() {
        listComicsView.showLoading();

        getListComicsUseCase.execute(idCharacter, offset)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Comic>>() {
                    @Override
                    public void accept(@NonNull List<Comic> comics) throws Exception {
                        Log.d(TAG, "accept: "+comics.size());
                        offset = offset + comics.size();
                        showComic(comicDomainMapper.domainToView(comics));
                        listComicsView.hideLoading();
                    }

                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        ///TODO Handle error properly
                        Log.i(TAG, "onError: OH NO"+ Log.getStackTraceString(throwable));
                        listComicsView.showError();
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
