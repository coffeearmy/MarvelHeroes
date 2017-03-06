package com.coffeearmy.marvelheroes.listComics;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.coffeearmy.marvelheroes.R;
import com.coffeearmy.marvelheroes.base.BaseViewHolder;
import com.coffeearmy.marvelheroes.model.Comic;

import java.util.ArrayList;
import java.util.List;


public class ComicsRecyclerAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private List<Comic> comics =new ArrayList<>();
    private int itemLayout= R.layout.comic_list_item;

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        return new ComicViewHolder(v);

    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.bind(comics.get(position));
    }

    @Override
    public int getItemCount() {
        return comics.size();
    }

    public void addAll(List<Comic> operations) {
        this.comics.clear();
        this.comics.addAll(operations);
        notifyDataSetChanged();
    }
}
