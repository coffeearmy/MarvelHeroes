package com.coffeearmy.marvelheroes.data.net;

import com.coffeearmy.marvelheroes.data.entity.BaseResponse;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


/**
 *
 */

public interface ComicsGateway {

    @GET("characters/{characterId}/comics")
    Flowable<Response<BaseResponse>> getComicsByCharacterId(@Path("characterId") String characterId, @Query("offset") int offset);
}
