package com.coffeearmy.marvelheroes.injector.component;

import android.app.Activity;

import com.coffeearmy.marvelheroes.injector.ActivityScope;
import com.coffeearmy.marvelheroes.injector.module.ActivityModule;
import com.coffeearmy.marvelheroes.listComics.ListComicsActivity;
import com.coffeearmy.marvelheroes.listComics.ListComicsFragment;

import dagger.Component;

/**
 * :3
 */

@ActivityScope
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    //void inject(ListComicsActivity listComicsActivity);

    void inject(ListComicsFragment listComicsFragment);

    Activity getActivity();
}
