package com.coffeearmy.marvelheroes.base;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;

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
    }

    private void initializeButterKnife() {
        ButterKnife.bind(this);
    }


    protected abstract int getLayout();


}
