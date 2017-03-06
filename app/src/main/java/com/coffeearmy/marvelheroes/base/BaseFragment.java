package com.coffeearmy.marvelheroes.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.coffeearmy.marvelheroes.ComicApplication;
import com.coffeearmy.marvelheroes.injector.component.ApplicationComponent;

import butterknife.ButterKnife;

/**
 * :3
 */

public abstract class BaseFragment extends Fragment implements BaseViewModel{


    private BasePresenter presenter;

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
        return inflater.inflate(getLayout(),container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeButterKnife(view);
        presenter=initializePresenter();
        presenter.initialize(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.onPause();
    }

    private void initializeButterKnife(View view) {
        ButterKnife.bind(this,view);
    }

    public abstract int getLayout();

    public abstract BasePresenter initializePresenter();

}
