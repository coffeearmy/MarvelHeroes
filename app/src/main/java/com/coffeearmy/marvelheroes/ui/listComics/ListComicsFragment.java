package com.coffeearmy.marvelheroes.ui.listComics;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.coffeearmy.marvelheroes.R;
import com.coffeearmy.marvelheroes.base.BaseFragment;
import com.coffeearmy.marvelheroes.base.BasePresenter;
import com.coffeearmy.marvelheroes.injector.component.ApplicationComponent;
import com.coffeearmy.marvelheroes.injector.component.DaggerActivityComponent;
import com.coffeearmy.marvelheroes.injector.module.ActivityModule;
import com.coffeearmy.marvelheroes.model.ComicView;
import com.coffeearmy.marvelheroes.navigation.Navigator;
import com.coffeearmy.marvelheroes.ui.comicDetail.ComicDetailActivity;
import com.coffeearmy.marvelheroes.ui.listComics.list.ComicsRecyclerAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * :3
 */

public class ListComicsFragment extends BaseFragment implements ListComicsViewModel,
        ComicsRecyclerAdapter.OnClickItemListener{

    private static int layout= R.layout.list_comics_fragment;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @Inject
    ListComicsPresenter listComicsPresenter;
    @Inject
    Navigator navigator;

    private ComicsRecyclerAdapter comicsRecyclerAdapter;
    private boolean loading;

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
        comicsRecyclerAdapter = new ComicsRecyclerAdapter(this);
        recyclerView.setAdapter(comicsRecyclerAdapter);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setHasFixedSize(true);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(dy>0 && !loading) {
                    int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
                    int visibleItems = recyclerView.getChildCount();
                    int totalItems = comicsRecyclerAdapter.getItemCount();
                    if (listComicsPresenter.needsMoreItems(firstVisibleItemPosition, visibleItems, totalItems))
                        listComicsPresenter.loadMoreItems();
                }
            }
        });
    }

    @Override
    public void showLoading() {
        super.showLoading();
        loading=true;
    }

    @Override
    public void hideLoading() {
        super.hideLoading();
        loading=false;
    }

    @Override
    public void showComics(List<ComicView> comics) {
        comicsRecyclerAdapter.addAll(comics);
    }

    @Override
    public void onClickItem(ComicView comic) {
        navigator.goToDetail(getActivity(),comic);
    }
}
