package com.coffeearmy.marvelheroes.ui.comicDetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.MenuItem;

import com.coffeearmy.marvelheroes.R;
import com.coffeearmy.marvelheroes.base.BaseActivity;
import com.coffeearmy.marvelheroes.injector.component.ActivityComponent;
import com.coffeearmy.marvelheroes.injector.component.ApplicationComponent;
import com.coffeearmy.marvelheroes.injector.component.DaggerActivityComponent;
import com.coffeearmy.marvelheroes.injector.module.ActivityModule;
import com.coffeearmy.marvelheroes.model.ComicView;
import com.coffeearmy.marvelheroes.navigation.Navigator;

import javax.inject.Inject;

/**
 * :3
 */

public class ComicDetailActivity extends BaseActivity{

    private static final String INTENT_DATA_COMIC = "bundle.data.comic";
    private final int layoutID= R.layout.activity_main;

    @Inject
    Navigator navigator;

    private ActivityComponent activityComponent;

    public static Intent newInstance(Context context, ComicView comic) {
        Intent intent = new Intent(context, ComicDetailActivity.class);
        intent.putExtra(INTENT_DATA_COMIC,comic);
        return intent;
    }

    @Override
    protected String getToolbarTitle() {
        return getString(R.string.app_name);
    }

    @Override
    protected boolean isHomeButtonNeeded() {
        return true;
    }

    @Override
    protected int getLayout() {
        return layoutID;
    }

    @NonNull
    @Override
    protected Fragment onRequestFragment() {
        ComicView comicView =  getIntent().getExtras().getParcelable(INTENT_DATA_COMIC);
        return ComicDetailFragment.newInstance( comicView);
    }

    @Override
    protected ActivityComponent getComponent() {
        return activityComponent;
    }

    protected void inject(ApplicationComponent applicationComponent) {
       activityComponent= DaggerActivityComponent.builder()
                .applicationComponent(applicationComponent)
                .activityModule(new ActivityModule(this))
                .build();
        activityComponent.inject(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            navigator.goToList(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
