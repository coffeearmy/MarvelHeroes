package com.coffeearmy.marvelheroes.data.net;

import com.coffeearmy.marvelheroes.data.entity.BaseResponse;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


/**
 * :3
 */

public interface ComicsGateway {

    @GET("characters/{characterId}/comics")
    Observable<BaseResponse> getComicsByCharacterId(@Path("characterId") String characterId);
}
