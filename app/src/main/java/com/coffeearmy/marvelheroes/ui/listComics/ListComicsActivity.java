package com.coffeearmy.marvelheroes.ui.listComics;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.coffeearmy.marvelheroes.R;
import com.coffeearmy.marvelheroes.base.BaseActivity;
import com.coffeearmy.marvelheroes.injector.component.ActivityComponent;
import com.coffeearmy.marvelheroes.injector.component.ApplicationComponent;
import com.coffeearmy.marvelheroes.injector.component.DaggerActivityComponent;
import com.coffeearmy.marvelheroes.injector.module.ActivityModule;


public class ListComicsActivity extends BaseActivity {

    private static final String TAG = ListComicsActivity.class.getSimpleName();
    private final int layoutID= R.layout.activity_main;
    private ActivityComponent activityComponent;

    public static Intent newInstance(Context context) {
        return new Intent(context,ListComicsActivity.class);
    }

    @Override
    protected String getToolbarTitle() {
        return getString(R.string.app_name);
    }

    @Override
    protected boolean isHomeButtonNeeded() {
        return false;
    }

    @Override
    protected int getLayout() {
        return layoutID;
    }

    protected void inject(ApplicationComponent applicationComponent) {
        activityComponent= DaggerActivityComponent.builder()
                .applicationComponent(applicationComponent)
                .activityModule(new ActivityModule(this))
                .build();
        activityComponent.inject(this);
    }


    @NonNull
    @Override
    protected Fragment onRequestFragment() {
        return new ListComicsFragment();
    }

    @Override
    protected ActivityComponent getComponent() {
        return activityComponent;
    }


}
