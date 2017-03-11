package com.coffeearmy.marvelheroes.data.repository.datasource;

import com.coffeearmy.marvelheroes.data.entity.BaseResponse;

import io.reactivex.Flowable;
import retrofit2.Response;

/**
 * :3
 */

public class DataBaseDataStore implements  BaseDataStore {


    @Override
    public Flowable<Response<BaseResponse>> getComicsByCharacterId(String characterId, int offset) {
        ///TODO IMPLEMENT WITH REALM
        return null;
    }
}
