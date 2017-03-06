package com.coffeearmy.marvelheroes.model;

import java.util.List;

/**
 * :3
 */

public class Comic implements BaseEntity{
    private int id;
    private String title;
    private String description;
    private
    List<String> comicImageList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getComicImageList() {
        return comicImageList;
    }

    public void setComicImageList(List<String> comicImageList) {
        this.comicImageList = comicImageList;
    }
}
