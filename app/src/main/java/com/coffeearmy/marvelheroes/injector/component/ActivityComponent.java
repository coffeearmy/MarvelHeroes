package com.coffeearmy.marvelheroes.injector.component;

import android.app.Activity;

import com.coffeearmy.marvelheroes.injector.ActivityScope;
import com.coffeearmy.marvelheroes.injector.module.ActivityModule;
import com.coffeearmy.marvelheroes.ui.comicDetail.ComicDetailActivity;
import com.coffeearmy.marvelheroes.ui.comicDetail.ComicDetailFragment;
import com.coffeearmy.marvelheroes.ui.listComics.ListComicsActivity;
import com.coffeearmy.marvelheroes.ui.listComics.ListComicsFragment;

import dagger.Component;

/**
 *
 */

@ActivityScope
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(ListComicsActivity listComicsActivity);

    void inject(ComicDetailActivity comicDetailActivity);

    void inject(ListComicsFragment listComicsFragment);

    void inject(ComicDetailFragment comicDetailFragment);

    Activity getActivity();
}
