package com.strikalov.photoproject.presenter;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.strikalov.photoproject.App;
import com.strikalov.photoproject.model.Model;
import com.strikalov.photoproject.view.DetailView;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

@InjectViewState
public class DetailPresenter extends MvpPresenter<DetailView> {

    private static final String TAG = "DetailPresenter";

    private Model model;
    private Disposable databaseDisposable;

    @Inject
    public DetailPresenter(Model model){
        this.model = model;
    }

    public void onGetPhotoId(int id){
        databaseDisposable = model.getPhotoByIdFromDatabase(id).observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        photoRoomEntity -> {
                            if(photoRoomEntity != null){
                                getViewState().downloadImage(photoRoomEntity.getPhotoUrl());
                            }
                        },
                        throwable -> Log.i(TAG, throwable.toString())
                );
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(databaseDisposable != null){
            databaseDisposable.dispose();
        }
    }
}
