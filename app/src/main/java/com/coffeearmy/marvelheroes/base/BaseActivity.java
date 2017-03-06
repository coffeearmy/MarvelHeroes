package com.coffeearmy.marvelheroes.base;

import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.coffeearmy.marvelheroes.ComicApplication;
import com.coffeearmy.marvelheroes.R;
import com.coffeearmy.marvelheroes.injector.component.ApplicationComponent;

import butterknife.ButterKnife;

/**
 * :3
 */

public abstract class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        initializeButterKnife();
        injectFragment();
    }

    private void initializeButterKnife() {
        ButterKnife.bind(this);
    }


    protected abstract int getLayout();

    private void injectFragment() {
        final Fragment fragment = onRequestFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.content_place_holder, fragment)
                .commit();
    }

    @NonNull
    protected abstract Fragment onRequestFragment();

}
