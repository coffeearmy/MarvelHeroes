package com.coffeearmy.marvelheroes.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Object used to mapper the comic api response, also knownn as DTO
 */

public class ComicsResponse {
    private Integer offset;
    private Integer limit;
    private Integer total;
    private Integer count;
    @SerializedName("results")
    private
    List<ComicResponse> comicResponseList;

    public void setCount(Integer count) {
        this.count = count;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public List<ComicResponse> getComicResponseList() {
        return comicResponseList;
    }

    public void setComicResponseList(List<ComicResponse> comicResponseList) {
        this.comicResponseList = comicResponseList;
    }
}
