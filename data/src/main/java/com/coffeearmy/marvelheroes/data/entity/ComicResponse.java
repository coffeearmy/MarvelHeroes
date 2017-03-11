package com.coffeearmy.marvelheroes.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 *
 */

public class ComicResponse {
    private Integer id;
    private String title;
    private String description;
    @SerializedName("images")
    private
    List<ComicImage> comicImageList;

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

    public List<ComicImage> getComicImageList() {
        return comicImageList;
    }

    public void setComicImageList(List<ComicImage> comicImageList) {
        this.comicImageList = comicImageList;
    }

    public class ComicImage {
        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        String path;

        public String getExtension() {
            return extension;
        }

        public void setExtension(String extension) {
            this.extension = extension;
        }

        String extension;
    }
}
