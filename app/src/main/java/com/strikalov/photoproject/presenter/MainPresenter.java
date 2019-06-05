package com.strikalov.photoproject.presenter;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.strikalov.photoproject.model.entity.Photo;
import com.strikalov.photoproject.model.network.PixabayApi;
import com.strikalov.photoproject.view.IViewHolder;
import com.strikalov.photoproject.view.MainView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    private static final String TAG = "MainPresenter";

    private RecyclerMainPresenter recyclerMainPresenter;
    private PixabayApi pixabayApi;
    private List<Photo> photos;
    private Disposable disposable;

    public MainPresenter(){
        recyclerMainPresenter = new RecyclerMainPresenter();
        pixabayApi = new PixabayApi();
        photos = new ArrayList<>();
    }

    @Override
    protected void onFirstViewAttach() {
        downloadPhotoList();
    }

    private void downloadPhotoList(){
        disposable = pixabayApi.loadPhotoList().observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        photoList -> {
                            photos = photoList.getPhotoList();
                            getViewState().updateRecyclerView();
                            },
                        throwable -> Log.i(TAG, throwable.toString())
                );
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(disposable != null){
            disposable.dispose();
        }
    }

    public IRecyclerMainPresenter getRecyclerMainPresenter() {
        return recyclerMainPresenter;
    }

    private class RecyclerMainPresenter implements IRecyclerMainPresenter{


        @Override
        public void bindView(IViewHolder holder) {

            holder.setImage(photos.get(holder.getPos()).getWebformatURL());

        }

        @Override
        public int getItemCount() {
            return photos.size();
        }

        @Override
        public void onRecyclerItemClick(int position) {
            Log.i(TAG, "Position: " + position);
            getViewState().startDetailActivity(photos.get(position).getWebformatURL());
        }
    }
}
