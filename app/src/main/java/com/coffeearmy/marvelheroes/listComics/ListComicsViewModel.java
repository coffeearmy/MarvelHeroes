package com.coffeearmy.marvelheroes.listComics;

import com.coffeearmy.marvelheroes.base.BaseViewModel;
import com.coffeearmy.marvelheroes.model.Comic;

import java.util.List;

/**
 * :3
 */

public interface ListComicsViewModel extends BaseViewModel {

    void showComics(List<Comic> comics);
}
