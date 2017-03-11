package com.coffeearmy.marvelheroes.useCases;

import com.coffeearmy.marvelheroes.model.Comic;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;

/**
 *
 */
public interface GetListComicsUseCase {

    Flowable<List<Comic>> execute(String idCharacter, int offset);
}
