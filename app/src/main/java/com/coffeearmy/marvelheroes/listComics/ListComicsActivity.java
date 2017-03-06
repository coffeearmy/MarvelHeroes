package com.coffeearmy.marvelheroes.listComics;


import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.coffeearmy.marvelheroes.R;
import com.coffeearmy.marvelheroes.base.BaseActivity;


public class ListComicsActivity extends BaseActivity {

    private static final String TAG = ListComicsActivity.class.getSimpleName();
    private final int layoutID= R.layout.activity_main;


    @Override
    protected int getLayout() {
        return layoutID;
    }


    @NonNull
    @Override
    protected Fragment onRequestFragment() {
        return new ListComicsFragment();
    }

}
