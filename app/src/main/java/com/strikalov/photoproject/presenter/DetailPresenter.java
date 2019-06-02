package com.strikalov.photoproject.presenter;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.strikalov.photoproject.view.DetailView;

@InjectViewState
public class DetailPresenter extends MvpPresenter<DetailView> {

    private static final String TAG = "DetailPresenter";

    public void onGetPosition(int position){
        Log.i(TAG, "Image position = " + position);
    }

}
