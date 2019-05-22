package com.strikalov.photoproject.presenter;

import android.util.Log;

import com.strikalov.photoproject.model.ImageModel;
import com.strikalov.photoproject.view.IViewHolder;

import java.util.List;

public class MainPresenter {

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
        public void onRecyclerItemClick() {
            Log.i("MainPresenter", Integer.toString(imageModel.incClickCount()));
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
