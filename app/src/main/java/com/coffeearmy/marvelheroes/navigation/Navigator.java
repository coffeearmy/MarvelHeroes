package com.coffeearmy.marvelheroes.navigation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.coffeearmy.marvelheroes.model.Comic;
import com.coffeearmy.marvelheroes.model.ComicView;
import com.coffeearmy.marvelheroes.ui.comicDetail.ComicDetailActivity;


import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * :3
 */
@Singleton
public class Navigator {

    @Inject
    public Navigator() {
    }

    public void goToDetail(Activity activity, ComicView comic){
        if (activity != null) {
            Intent intentToLaunch = ComicDetailActivity.newInstance(activity, comic);
            activity.startActivity(intentToLaunch);
        }
    }


}
