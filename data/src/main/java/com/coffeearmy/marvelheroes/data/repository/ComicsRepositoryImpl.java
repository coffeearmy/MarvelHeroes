package com.coffeearmy.marvelheroes.data.repository;

import com.coffeearmy.marvelheroes.data.entity.BaseResponse;
import com.coffeearmy.marvelheroes.data.entity.mapper.ComicResponseMapper;
import com.coffeearmy.marvelheroes.data.net.ComicsGateway;
import com.coffeearmy.marvelheroes.model.Comic;
import com.coffeearmy.marvelheroes.repository.ComicsRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;


/**
 * :3
 */

public class ComicsRepositoryImpl implements ComicsRepository {

    private ComicsGateway comicsGateway;
    private ComicResponseMapper comicResponseMapper;

    @Inject
    public ComicsRepositoryImpl(ComicsGateway comicsGateway, ComicResponseMapper comicResponseMapper) {
        this.comicsGateway = comicsGateway;
        this.comicResponseMapper = comicResponseMapper;
    }


    @Override
    public Observable<List<Comic>> getListComics(String characterId) {
        return comicsGateway.getComicsByCharacterId(characterId)
               .flatMap(new Function<BaseResponse, Observable<List<Comic>>>() {
                   @Override
                   public Observable<List<Comic>> apply(@NonNull BaseResponse baseResponse) throws Exception {
                      return comicResponseMapper.responseToModel(baseResponse.getData());
                   }
               });
    }

}
