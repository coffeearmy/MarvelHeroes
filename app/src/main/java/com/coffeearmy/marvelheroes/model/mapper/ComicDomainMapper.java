package com.coffeearmy.marvelheroes.model.mapper;

import com.coffeearmy.marvelheroes.data.entity.ComicResponse;
import com.coffeearmy.marvelheroes.data.entity.ComicsResponse;
import com.coffeearmy.marvelheroes.injector.ActivityScope;
import com.coffeearmy.marvelheroes.model.Comic;
import com.coffeearmy.marvelheroes.model.ComicView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;


/**
 *
 */
@ActivityScope
public class ComicDomainMapper {

    @Inject
    public ComicDomainMapper() {
    }

    public List<ComicView> domainToView(List<Comic> data) {
        List<ComicView> comicList = new ArrayList<>();
        for (Comic comic : data) {
            ComicView comicView = new ComicView();
            comicView.setTitle(comic.getTitle());
            comicView.setId(comic.getId());
            comicView.setDescription(comic.getDescription());
            comicView.setComicImageList(comic.getComicImageList());
            comicList.add(comicView);
        }
        return comicList;
    }
}
