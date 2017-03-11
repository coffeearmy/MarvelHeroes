package com.coffeearmy.marvelheroes.navigation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.coffeearmy.marvelheroes.model.Comic;
import com.coffeearmy.marvelheroes.model.ComicView;
import com.coffeearmy.marvelheroes.ui.comicDetail.ComicDetailActivity;
import com.coffeearmy.marvelheroes.ui.listComics.ListComicsActivity;


import javax.inject.Inject;
import javax.inject.Singleton;

/**
 *
 */
@Singleton
public class Navigator {

    @Inject
    public Navigator() {
    }

    public void goToDetail(Context context, ComicView comic){
        if (context != null) {
            Intent intentToLaunch = ComicDetailActivity.newInstance(context, comic);
            context.startActivity(intentToLaunch);
        }
    }

    public void goToList(Context context){
        Intent intentToLaunch = ListComicsActivity.newInstance(context);
        context.startActivity(intentToLaunch);
    }


}
