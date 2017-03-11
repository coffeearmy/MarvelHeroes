package com.coffeearmy.marvelheroes.ui.listComics.list;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.coffeearmy.marvelheroes.R;
import com.coffeearmy.marvelheroes.base.BaseViewHolder;
import com.coffeearmy.marvelheroes.base.BaseEntity;
import com.coffeearmy.marvelheroes.model.ComicView;

import butterknife.BindView;

/**
 *
 */

public class ComicViewHolder extends BaseViewHolder {

    private View comicView;
    @BindView(R.id.title_comic)
    TextView titleComic;
    @BindView(R.id.cover_comic)
    ImageView coverComic;

    private OnClickCallback listener;

    public  interface  OnClickCallback{
        void getComicClicked(ComicView comic);
    }

    public ComicViewHolder(View comicView, ComicsRecyclerAdapter comicsRecyclerAdapter) {
        super(comicView);
        this.comicView = comicView;
        this.listener=comicsRecyclerAdapter;
    }

    @Override
    public void bind(BaseEntity model) {
        final ComicView comic = (ComicView) model;
        titleComic.setText(comic.getTitle());
        if(!comic.getComicImageList().isEmpty())
        Glide.with(comicView.getContext()).load(comic.getComicImageList().get(0)).into(coverComic);

        comicView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                listener.getComicClicked(comic);
            }
        });
    }

}
