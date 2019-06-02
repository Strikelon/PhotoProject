package com.strikalov.photoproject.view.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.squareup.picasso.Picasso;
import com.strikalov.photoproject.R;
import com.strikalov.photoproject.presenter.DetailPresenter;
import com.strikalov.photoproject.view.DetailView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends MvpAppCompatActivity implements DetailView {

    private static final String DETAIL_ACTIVITY_ARG = "DetailActivityArg";

    public static Intent newIntent(Context context, String photoUrl){
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(DETAIL_ACTIVITY_ARG, photoUrl);
        return intent;
    }

    @InjectPresenter
    public DetailPresenter detailPresenter;

    @BindView(R.id.detail_image)
    ImageView detailImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        if(getIntent() != null){
            String photo = getIntent().getStringExtra(DETAIL_ACTIVITY_ARG);
            detailPresenter.onGetPhotoUrl(photo);
        }
    }

    @Override
    public void downloadImage(String photoUrl) {
        Picasso.get()
                .load(photoUrl)
                .placeholder(R.drawable.no_photo_placeholder)
                .into(detailImage);
    }
}
