package com.strikalov.photoproject.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.arellomobile.mvp.DefaultView;
import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.strikalov.photoproject.R;
import com.strikalov.photoproject.presenter.DetailPresenter;
import com.strikalov.photoproject.view.DetailView;

public class DetailActivity extends MvpAppCompatActivity implements DetailView {

    private static final String DETAIL_ACTIVITY_ARG = "DetailActivityArg";

    public static Intent newIntent(Context context, int position){
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(DETAIL_ACTIVITY_ARG, position);
        return intent;
    }

    @InjectPresenter
    public DetailPresenter detailPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        if(getIntent() != null){
            int position = getIntent().getIntExtra(DETAIL_ACTIVITY_ARG, -1);
            detailPresenter.onGetPosition(position);
        }
    }
}
