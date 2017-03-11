package com.coffeearmy.marvelheroes.injector.module;

import android.app.Activity;

import com.coffeearmy.marvelheroes.data.entity.mapper.ComicResponseMapper;
import com.coffeearmy.marvelheroes.data.net.ComicsGateway;
import com.coffeearmy.marvelheroes.data.repository.ComicsRepositoryImpl;
import com.coffeearmy.marvelheroes.data.repository.datasource.DataStoreFactory;
import com.coffeearmy.marvelheroes.injector.ActivityScope;
import com.coffeearmy.marvelheroes.model.mapper.ComicDomainMapper;
import com.coffeearmy.marvelheroes.ui.comicDetail.ComicDetailPresenter;
import com.coffeearmy.marvelheroes.ui.listComics.ListComicsPresenter;
import com.coffeearmy.marvelheroes.repository.ComicsRepository;
import com.coffeearmy.marvelheroes.useCases.GetListComicsUseCase;
import com.coffeearmy.marvelheroes.useCases.GetListComicsUseCaseImpl;

import javax.inject.Named;
import javax.inject.Singleton;

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
    @Named("id_character")
    String provideIdCharacter() {
        return "1009415";
    }

    @Provides
    @ActivityScope
    ListComicsPresenter provideListComicsPresenter(GetListComicsUseCase getListComicsUseCase, ComicDomainMapper comicDomainMapper, @Named("id_character")String idCharacter){
        return  new ListComicsPresenter(getListComicsUseCase,comicDomainMapper,idCharacter);
    }

    @Provides
    @ActivityScope
    GetListComicsUseCase provideGetListComicsUseCase(ComicsRepository comicsRepository){
        return new GetListComicsUseCaseImpl(comicsRepository);
    }

    @Provides
    @ActivityScope
    ComicsRepository provideComicRepository(DataStoreFactory dataStoreFactory, ComicResponseMapper comicResponseMapper){
        return new ComicsRepositoryImpl(dataStoreFactory,comicResponseMapper);
    }

}
