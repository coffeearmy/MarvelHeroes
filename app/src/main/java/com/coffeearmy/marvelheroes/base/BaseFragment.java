package com.coffeearmy.marvelheroes.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.coffeearmy.marvelheroes.ComicApplication;
import com.coffeearmy.marvelheroes.R;
import com.coffeearmy.marvelheroes.injector.component.ApplicationComponent;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

/**
 * :3
 */

public abstract class BaseFragment extends Fragment implements BaseViewModel {


    @Nullable
    @BindView(R.id.loading)
    View loadingView;

    protected BasePresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject(getApplicationComponent());
    }

    protected abstract void inject(ApplicationComponent applicationComponent);

    private ApplicationComponent getApplicationComponent() {
        return ((ComicApplication) getActivity().getApplication()).getComponent();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayout(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeButterKnife(view);
        presenter = initializePresenter();
        presenter.attachView(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onViewReady();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.pause();
    }

    @Override
    public void showLoading() {
        if (loadingView != null)
            loadingView.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideLoading() {
        if (loadingView != null)
            loadingView.setVisibility(View.GONE);
    }

    @Override
    public void showError() {
        //A better way to do it is showing a dialog, or a text explaining what happend
        hideLoading();
        Toast.makeText(getContext(), getString(R.string.default_error), Toast.LENGTH_LONG).show();
    }

    private void initializeButterKnife(View view) {
        ButterKnife.bind(this, view);
    }

    public abstract int getLayout();

    public abstract BasePresenter initializePresenter();

}
