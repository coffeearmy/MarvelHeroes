package com.coffeearmy.marvelheroes.data.repository.datasource;

import com.coffeearmy.marvelheroes.data.entity.BaseResponse;

import io.reactivex.Flowable;
import retrofit2.Response;

/**
 *
 */

public interface BaseDataStore {

    Flowable<Response<BaseResponse>> getComicsByCharacterId(String characterId, int offset);
}
