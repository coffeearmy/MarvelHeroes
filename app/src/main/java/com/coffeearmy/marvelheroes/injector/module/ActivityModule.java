package com.coffeearmy.marvelheroes.injector.module;

import android.app.Activity;

import com.coffeearmy.marvelheroes.data.entity.mapper.ComicResponseMapper;
import com.coffeearmy.marvelheroes.data.net.ComicsGateway;
import com.coffeearmy.marvelheroes.data.repository.ComicsRepositoryImpl;
import com.coffeearmy.marvelheroes.injector.ActivityScope;
import com.coffeearmy.marvelheroes.listComics.ListComicsPresenter;
import com.coffeearmy.marvelheroes.repository.ComicsRepository;
import com.coffeearmy.marvelheroes.useCases.GetListComicsUseCase;
import com.coffeearmy.marvelheroes.useCases.GetListComicsUseCaseImpl;

import dagger.Module;
import dagger.Provides;

/**
 * :3
 */
@Module
public class ActivityModule {

    private final Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    /**
     * Expose the activity to dependents in the graph.
     */
    @Provides
    @ActivityScope
    Activity provideActivity() {
        return this.activity;
    }


    @Provides
    ComicResponseMapper provideComicResponseMapper(){
        return new ComicResponseMapper();
    }


    @Provides
    @ActivityScope
    ListComicsPresenter provideListComicsPresenter(GetListComicsUseCase getListComicsUseCase){
        return  new ListComicsPresenter(getListComicsUseCase);
    }

    @Provides
    @ActivityScope
    GetListComicsUseCase provideGetListComicsUseCase(ComicsRepository comicsRepository){
        return new GetListComicsUseCaseImpl(comicsRepository);
    }

    @Provides
    @ActivityScope
    ComicsRepository provideComicRepository(ComicsGateway comicsGateway, ComicResponseMapper comicResponseMapper){
        return new ComicsRepositoryImpl(comicsGateway,comicResponseMapper);
    }
}