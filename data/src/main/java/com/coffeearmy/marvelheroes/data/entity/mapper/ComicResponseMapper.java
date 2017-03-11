package com.coffeearmy.marvelheroes.data.entity.mapper;

import com.coffeearmy.marvelheroes.data.entity.ComicResponse;
import com.coffeearmy.marvelheroes.data.entity.ComicsResponse;
import com.coffeearmy.marvelheroes.model.Comic;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;


/**
 *
 */

public class ComicResponseMapper {

    public ComicResponseMapper() {
    }

    public Flowable<List<Comic>> responseToModel(ComicsResponse data) {
        List<Comic> comicList = new ArrayList<>();
        for (ComicResponse comicResponse : data.getComicResponseList()) {
            Comic comic = new Comic();
            comic.setTitle(comicResponse.getTitle());
            comic.setId(comicResponse.getId());
            comic.setDescription(comicResponse.getDescription());
            comic.setComicImageList(responseImageToModel(comicResponse.getComicImageList()));
            comicList.add(comic);
        }
        return Flowable.just(comicList);
    }

    private List<String> responseImageToModel(List<ComicResponse.ComicImage> comicImageList) {
        List<String> urlList= new ArrayList<>();
        for (ComicResponse.ComicImage comicImage : comicImageList) {
            urlList.add(comicImage.getPath()+"."+comicImage.getExtension());
        }
        return urlList;
    }


}
