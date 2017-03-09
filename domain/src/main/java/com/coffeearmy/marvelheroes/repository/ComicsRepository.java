package com.coffeearmy.marvelheroes.repository;

import com.coffeearmy.marvelheroes.model.Comic;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;


/**
 * :3
 */

public interface ComicsRepository {

    Flowable<List<Comic>> getListComics(String characterId, int offset);

}
