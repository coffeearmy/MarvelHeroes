package com.coffeearmy.marvelheroes.base;

import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.coffeearmy.marvelheroes.ComicApplication;
import com.coffeearmy.marvelheroes.R;
import com.coffeearmy.marvelheroes.injector.component.ActivityComponent;
import com.coffeearmy.marvelheroes.injector.component.ApplicationComponent;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * :3
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Nullable
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject(getApplicationComponent());
        setContentView(getLayout());
        initializeButterKnife();
        initializeToolbar(getToolbarTitle(),isHomeButtonNeeded());
        injectFragment();
    }

    private void initializeToolbar(String title, boolean needHomeButton) {
        if(toolbar!=null) {
            setSupportActionBar(toolbar);
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(needHomeButton);
            }
            if (title != null && !title.isEmpty()) {
                toolbar.setTitle(title);
            }
        }
    }

    private void initializeButterKnife() {
        ButterKnife.bind(this);
    }

    private void injectFragment() {
        final Fragment fragment = onRequestFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.content_place_holder, fragment)
                .commit();
    }

    private ApplicationComponent getApplicationComponent() {
        return ((ComicApplication) getApplication()).getComponent();
    }

    protected abstract void inject(ApplicationComponent applicationComponent);

    @NonNull
    protected abstract Fragment onRequestFragment();

    protected abstract ActivityComponent getComponent();

    protected abstract String getToolbarTitle();

    protected abstract boolean isHomeButtonNeeded();

    protected abstract int getLayout();

}
