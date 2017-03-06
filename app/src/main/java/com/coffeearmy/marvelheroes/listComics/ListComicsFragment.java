package com.coffeearmy.marvelheroes.listComics;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.coffeearmy.marvelheroes.R;
import com.coffeearmy.marvelheroes.base.BaseFragment;
import com.coffeearmy.marvelheroes.base.BasePresenter;
import com.coffeearmy.marvelheroes.model.Comic;
import com.coffeearmy.marvelheroes.injector.component.ApplicationComponent;
import com.coffeearmy.marvelheroes.injector.component.DaggerActivityComponent;
import com.coffeearmy.marvelheroes.injector.module.ActivityModule;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * :3
 */

public class ListComicsFragment extends BaseFragment implements ListComicsViewModel {

    private static int layout= R.layout.list_comics_fragment;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Inject
    ListComicsPresenter listComicsPresenter;

    private ComicsRecyclerAdapter comicsRecyclerAdapter;

    @Override
    protected void inject(ApplicationComponent applicationComponent) {
        DaggerActivityComponent.builder()
                .applicationComponent(applicationComponent)
                .activityModule(new ActivityModule(getActivity()))
                .build().inject(this);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeList();
    }

    @Override
    public int getLayout() {
        return layout;
    }

    @Override
    public BasePresenter initializePresenter() {
        return listComicsPresenter;
    }


    private void initializeList() {
        comicsRecyclerAdapter = new ComicsRecyclerAdapter();
        recyclerView.setAdapter(comicsRecyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void showComics(List<Comic> comics) {
        comicsRecyclerAdapter.addAll(comics);
    }
}
