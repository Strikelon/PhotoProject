package com.strikalov.photoproject.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.strikalov.photoproject.R;
import com.strikalov.photoproject.presenter.IRecyclerMainPresenter;
import com.strikalov.photoproject.view.IViewHolder;

public class ImageRecyclerAdapter extends RecyclerView.Adapter<ImageRecyclerAdapter.ImageHolder> {

    private IRecyclerMainPresenter iRecyclerMainPresenter;

    public ImageRecyclerAdapter(IRecyclerMainPresenter iRecyclerMainPresenter){
        this.iRecyclerMainPresenter = iRecyclerMainPresenter;
    }

    class ImageHolder extends RecyclerView.ViewHolder implements IViewHolder {

        private ImageView imageView;
        private int position;

        public ImageHolder(View view){
            super(view);
            imageView = view.findViewById(R.id.image_view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iRecyclerMainPresenter.onRecyclerItemClick();
                }
            });
        }

        @Override
        public void setImage(Boolean booleanValue) {
            if(booleanValue){
                imageView.setImageResource(R.drawable.m2);
            }
        }

        @Override
        public int getPos() {
            return position;
        }

    }

    @NonNull
    @Override
    public ImageHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_item, viewGroup, false);
        return new ImageHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageHolder imageHolder, int i) {
        imageHolder.position = i;
        iRecyclerMainPresenter.bindView(imageHolder);
    }

    @Override
    public int getItemCount() {
        return iRecyclerMainPresenter.getItemCount();
    }
}
