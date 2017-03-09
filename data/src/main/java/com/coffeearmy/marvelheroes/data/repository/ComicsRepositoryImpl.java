package com.coffeearmy.marvelheroes.data.repository;

import com.coffeearmy.marvelheroes.data.entity.BaseResponse;
import com.coffeearmy.marvelheroes.data.entity.mapper.ComicResponseMapper;
import com.coffeearmy.marvelheroes.data.exception.ApiException;
import com.coffeearmy.marvelheroes.data.net.ComicsGateway;
import com.coffeearmy.marvelheroes.model.Comic;
import com.coffeearmy.marvelheroes.repository.ComicsRepository;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import okhttp3.Response;
import retrofit2.http.HTTP;


/**
 * :3
 */

public class ComicsRepositoryImpl implements ComicsRepository {

    private static final int OK_STATUS = 200;
    private ComicsGateway comicsGateway;
    private ComicResponseMapper comicResponseMapper;

    @Inject
    public ComicsRepositoryImpl(ComicsGateway comicsGateway, ComicResponseMapper comicResponseMapper) {
        this.comicsGateway = comicsGateway;
        this.comicResponseMapper = comicResponseMapper;
    }


    @Override
    public Flowable<List<Comic>> getListComics(String characterId, int offset) {
        return comicsGateway.getComicsByCharacterId(characterId,offset)
                //Checking errors of the  API
               .flatMap(new Function<retrofit2.Response<BaseResponse>, Flowable<BaseResponse>>() {
                    @Override
                    public Flowable<BaseResponse> apply(@NonNull retrofit2.Response<BaseResponse> response) throws Exception {

                        if(response.code()!=OK_STATUS) //HTTP errors
                            throw new ApiException(response.message());

                        if(response.body().getCode()!= OK_STATUS) //API errors
                            throw new ApiException(response.body().getMessage());

                        return Flowable.just(response.body());
                    }
                })
                //Map the Api object to the Domain object
               .flatMap(new Function<BaseResponse, Flowable<List<Comic>>>() {
                   @Override
                   public Flowable<List<Comic>> apply(@NonNull BaseResponse baseResponse) throws Exception {

                      return comicResponseMapper.responseToModel(baseResponse.getData());
                   }
               });
    }

}
