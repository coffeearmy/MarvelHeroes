package com.coffeearmy.marvelheroes.listComics;

import android.util.Log;

import com.coffeearmy.marvelheroes.base.BasePresenter;
import com.coffeearmy.marvelheroes.model.Comic;
import com.coffeearmy.marvelheroes.useCases.GetListComicsUseCase;
import com.coffeearmy.marvelheroes.useCases.GetListComicsUseCaseImpl;

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

    private ListComicsViewModel listComicsView;

    @Inject
    public ListComicsPresenter(GetListComicsUseCase getListComicsUseCase) {
        this.getListComicsUseCase = getListComicsUseCase;
    }

    @Override
    public void initialize(ListComicsViewModel view) {

        listComicsView = view;
        getListComicsUseCase.execute("1009415")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<List<Comic>>() {
                    @Override
                    public void onNext(List<Comic> comics) {
                        Log.i(TAG, "onNext: "+comics.size());
                        showComic(comics);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError: OHNO"+ Log.getStackTraceString(e));
                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG, "Complete");
                    }
                });
    }

    private void showComic(List<Comic> comics) {
        listComicsView.showComics(comics);
    }

    @Override
    public void onPause() {

    }


}
