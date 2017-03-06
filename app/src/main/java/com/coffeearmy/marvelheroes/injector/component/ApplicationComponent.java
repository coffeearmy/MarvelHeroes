package com.coffeearmy.marvelheroes.injector.component;

import android.app.Application;

import com.coffeearmy.marvelheroes.base.BaseActivity;
import com.coffeearmy.marvelheroes.data.net.ComicsGateway;
import com.coffeearmy.marvelheroes.injector.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * :3
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(Application application);

    ComicsGateway provideComicsGateway();

}
