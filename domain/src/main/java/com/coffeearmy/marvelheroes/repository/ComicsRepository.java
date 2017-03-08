package com.coffeearmy.marvelheroes.repository;

import com.coffeearmy.marvelheroes.model.Comic;

import java.util.List;

import io.reactivex.Observable;


/**
 * :3
 */

public interface ComicsRepository {

    Observable<List<Comic>> getListComics(String characterId, int offset);

}
