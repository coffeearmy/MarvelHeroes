package com.coffeearmy.marvelheroes.ui.listComics.list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.coffeearmy.marvelheroes.R;
import com.coffeearmy.marvelheroes.base.BaseViewHolder;
import com.coffeearmy.marvelheroes.model.ComicView;

import java.util.ArrayList;
import java.util.List;


public class ComicsRecyclerAdapter extends RecyclerView.Adapter<BaseViewHolder> implements ComicViewHolder.OnClickCallback {
    private List<ComicView> comics =new ArrayList<>();
    private int itemLayout= R.layout.comic_list_item;

    OnClickItemListener onClickItemListener;

    public ComicsRecyclerAdapter(OnClickItemListener onClickItemListener) {
        this.onClickItemListener = onClickItemListener;
    }

    public interface OnClickItemListener{
       void onClickItem(ComicView comic);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        return new ComicViewHolder(v,this);

    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.bind(comics.get(position));
    }

    @Override
    public int getItemCount() {
        return comics.size();
    }

    public void addAll(List<ComicView> operations) {
        this.comics.addAll(operations);
        notifyDataSetChanged();
    }

    @Override
    public void getComicClicked(ComicView comic) {
        onClickItemListener.onClickItem(comic);
    }
}
