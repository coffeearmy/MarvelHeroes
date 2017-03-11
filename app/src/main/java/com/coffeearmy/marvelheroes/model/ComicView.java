package com.coffeearmy.marvelheroes.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.coffeearmy.marvelheroes.base.BaseEntity;

import java.util.List;

/**
 *
 */

public class ComicView implements Parcelable, BaseEntity {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeStringList(this.comicImageList);
    }

    public ComicView() {
    }

    protected ComicView(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.description = in.readString();
        this.comicImageList = in.createStringArrayList();
    }

    public static final Creator<ComicView> CREATOR = new Creator<ComicView>() {
        @Override
        public ComicView createFromParcel(Parcel source) {
            return new ComicView(source);
        }

        @Override
        public ComicView[] newArray(int size) {
            return new ComicView[size];
        }
    };
}
