package com.coffeearmy.marvelheroes.data.repository.datasource;

import com.coffeearmy.marvelheroes.data.entity.BaseResponse;
import com.coffeearmy.marvelheroes.data.net.ComicsGateway;

import javax.inject.Inject;

import io.reactivex.Flowable;
import retrofit2.Response;

/**
 * :3
 */

public class DataStoreFactory {


    private ApiRestDataStore apiRestDataStore;

    private BaseDataStore selectedDataStore;

    public DataStoreFactory(ApiRestDataStore apiRestDataStore){
        this.apiRestDataStore = apiRestDataStore; //Add More
    }

    public void create(){
        //Logic for chossing the right Data store
        selectedDataStore=apiRestDataStore;
    }

    public Flowable<Response<BaseResponse>> getComicsByCharacterId(String characterId, int offset) {
        return selectedDataStore.getComicsByCharacterId(characterId,offset);
    }
}
