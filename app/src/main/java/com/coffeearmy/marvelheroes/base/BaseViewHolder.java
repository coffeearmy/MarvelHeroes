package com.coffeearmy.marvelheroes.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;


import butterknife.ButterKnife;

/**
 *
 */

public abstract class BaseViewHolder<T extends BaseEntity> extends RecyclerView.ViewHolder {
    public BaseViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public abstract void bind(T model);

}

