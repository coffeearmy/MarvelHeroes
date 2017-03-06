package com.coffeearmy.marvelheroes.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;


import com.coffeearmy.marvelheroes.model.BaseEntity;

import butterknife.ButterKnife;

/**
 * :3
 */

public abstract class BaseViewHolder<T extends BaseEntity> extends RecyclerView.ViewHolder {
    public BaseViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public abstract void bind(T model);

    public abstract View getHandlerView();
}

