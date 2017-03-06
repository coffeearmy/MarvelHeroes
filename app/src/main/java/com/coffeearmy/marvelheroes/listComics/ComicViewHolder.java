package com.coffeearmy.marvelheroes.listComics;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.coffeearmy.marvelheroes.R;
import com.coffeearmy.marvelheroes.base.BaseViewHolder;
import com.coffeearmy.marvelheroes.model.BaseEntity;
import com.coffeearmy.marvelheroes.model.Comic;

import butterknife.BindView;

/**
 * :3
 */

public class ComicViewHolder extends BaseViewHolder {

    private View comicView;
    @BindView(R.id.title_comic)
    TextView titleComic;
    @BindView(R.id.cover_comic)
    ImageView coverComic;

    public ComicViewHolder(View comicView) {
        super(comicView);
        this.comicView = comicView;
    }

    @Override
    public void bind(BaseEntity model) {
        Comic comic = (Comic) model;
        titleComic.setText(comic.getTitle());
        Glide.with(comicView.getContext()).load(comic.getComicImageList().get(0)).into(coverComic);
    }

    @Override
    public View getHandlerView() {
        return null;
    }
}
