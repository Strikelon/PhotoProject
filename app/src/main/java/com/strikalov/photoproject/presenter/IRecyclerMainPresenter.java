package com.strikalov.photoproject.presenter;

import com.strikalov.photoproject.view.IViewHolder;

public interface IRecyclerMainPresenter {
    void bindView(IViewHolder iViewHolder);

    int getItemCount();

    void onRecyclerItemClick(int position);
}
