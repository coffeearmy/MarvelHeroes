package com.coffeearmy.marvelheroes.useCases;

import com.coffeearmy.marvelheroes.model.Comic;
import com.coffeearmy.marvelheroes.repository.ComicsRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * :3
 */

public class GetListComicsUseCaseImpl implements GetListComicsUseCase<Observable<List<Comic>>> {

    private ComicsRepository comicsRepository;

    @Inject
    public GetListComicsUseCaseImpl(ComicsRepository comicsRepository) {
        this.comicsRepository = comicsRepository;
    }

    @Override
    public Observable<List<Comic>> execute(String idCharacter, int offset) {
        return  comicsRepository.getListComics(idCharacter,offset);
    }
}
