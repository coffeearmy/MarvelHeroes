package com.coffeearmy.marvelheroes.listComics;

import android.view.View;
import android.widget.TextView;

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

    public ComicViewHolder(View comicView) {
        super(comicView);
        this.comicView = comicView;
    }

    @Override
    public void bind(BaseEntity model) {
        Comic comic = (Comic) model;
        titleComic.setText(comic.getTitle());
    }

    @Override
    public View getHandlerView() {
        return null;
    }
}
