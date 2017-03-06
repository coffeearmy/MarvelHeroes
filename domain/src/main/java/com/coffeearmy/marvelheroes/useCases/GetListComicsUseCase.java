package com.coffeearmy.marvelheroes.useCases;

import com.coffeearmy.marvelheroes.model.Comic;

import java.util.List;

import io.reactivex.Observable;

/**
 * :3
 */
public interface GetListComicsUseCase<T> {

    Observable<List<Comic>> execute(String idCharacter);
}
