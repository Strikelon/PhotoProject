package com.strikalov.photoproject.presenter;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.strikalov.photoproject.model.ImageModel;
import com.strikalov.photoproject.view.IViewHolder;
import com.strikalov.photoproject.view.MainView;

import java.util.List;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    private static final String TAG = "MainPresenter";

    private class RecyclerMainPresenter implements IRecyclerMainPresenter{

        private ImageModel imageModel;
        private List<Boolean> list;

        public RecyclerMainPresenter(){
            imageModel = new ImageModel();
            list = imageModel.getList();
        }

        @Override
        public void bindView(IViewHolder holder) {

            holder.setImage(list.get(holder.getPos()));

        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        @Override
        public void onRecyclerItemClick(int position) {
            Log.i(TAG, "Position: " + position);
            getViewState().startDetailActivity(position);
        }
    }

    private RecyclerMainPresenter recyclerMainPresenter;

    public MainPresenter(){
        recyclerMainPresenter = new RecyclerMainPresenter();
    }

    public IRecyclerMainPresenter getRecyclerMainPresenter() {
        return recyclerMainPresenter;
    }
}
