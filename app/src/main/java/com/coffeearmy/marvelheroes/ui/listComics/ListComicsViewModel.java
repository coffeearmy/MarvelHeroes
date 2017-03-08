package com.coffeearmy.marvelheroes.ui.listComics;

import com.coffeearmy.marvelheroes.base.BaseViewModel;
import com.coffeearmy.marvelheroes.model.Comic;
import com.coffeearmy.marvelheroes.model.ComicView;

import java.util.List;

/**
 * :3
 */

public interface ListComicsViewModel extends BaseViewModel {

    void showComics(List<ComicView> comics);
}
