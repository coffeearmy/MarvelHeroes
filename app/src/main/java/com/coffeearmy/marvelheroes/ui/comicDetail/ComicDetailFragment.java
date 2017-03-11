package com.coffeearmy.marvelheroes.ui.comicDetail;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.coffeearmy.marvelheroes.R;
import com.coffeearmy.marvelheroes.base.BaseFragment;
import com.coffeearmy.marvelheroes.base.BasePresenter;
import com.coffeearmy.marvelheroes.injector.component.ActivityComponent;
import com.coffeearmy.marvelheroes.injector.component.ApplicationComponent;
import com.coffeearmy.marvelheroes.injector.component.DaggerActivityComponent;
import com.coffeearmy.marvelheroes.injector.module.ActivityModule;
import com.coffeearmy.marvelheroes.model.ComicView;
import com.coffeearmy.marvelheroes.navigation.Navigator;
import com.coffeearmy.marvelheroes.util.Util;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * :3
 */

public class ComicDetailFragment extends BaseFragment implements ComicDetailViewModel {

    private static final int LAYOUT_VIEW = R.layout.comic_detail_fragment;
    private static final String INTENT_DATA_COMIC_DETAIL = "bundle.data.comic.detail";

    @Inject
    ComicDetailPresenter comicDetailPresenter;

    @Inject
    Navigator navigator;

    @BindView(R.id.image_cover)
    ImageView imageCoverView;

    @BindView(R.id.comic_description)
    TextView description;

    @BindView(R.id.title_comic)
    TextView title;

    private ComicView comicDetail;

    public static ComicDetailFragment newInstance(ComicView comicView) {
        ComicDetailFragment fragment = new ComicDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(INTENT_DATA_COMIC_DETAIL, comicView);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public BasePresenter initializePresenter() {
        return comicDetailPresenter;
    }

    @Override
    protected void inject(ActivityComponent component) {
        component.inject(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null)
            comicDetail = getArguments().getParcelable(INTENT_DATA_COMIC_DETAIL);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showLoading();
        title.setText(comicDetail.getTitle());
        description.setText(comicDetail.getDescription());
        String uri = selectRandomUrl(comicDetail.getComicImageList());
        Glide.with(this).load(uri).asBitmap().into(target);
    }

    private String selectRandomUrl(List<String> comicImageList) {
        int randomIndex= Util.getRandom(0,comicImageList.size());
       return comicImageList.get(randomIndex);
    }
    private SimpleTarget target = new SimpleTarget<Bitmap>() {
        @Override
        public void onResourceReady(Bitmap bitmap, GlideAnimation glideAnimation) {
            imageCoverView.setImageBitmap( bitmap );
            hideLoading();
        }
    };

    @Override
    public int getLayout() {
        return LAYOUT_VIEW;
    }

}
