package com.coffeearmy.marvelheroes.data.repository.datasource;

import com.coffeearmy.marvelheroes.data.entity.BaseResponse;
import com.coffeearmy.marvelheroes.data.net.ComicsGateway;

import javax.inject.Inject;

import io.reactivex.Flowable;
import retrofit2.Response;

/**
 *
 */

public class ApiRestDataStore implements  BaseDataStore {

    private ComicsGateway comicsGateway;

    @Inject
    public ApiRestDataStore(ComicsGateway comicsGateway){ //Add More
        this.comicsGateway = comicsGateway;
    }

    @Override
    public Flowable<Response<BaseResponse>> getComicsByCharacterId(String characterId, int offset) {
        return comicsGateway.getComicsByCharacterId(characterId,offset);
    }
}
