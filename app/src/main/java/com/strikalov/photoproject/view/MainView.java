package com.strikalov.photoproject.view;

import com.arellomobile.mvp.MvpView;

public interface MainView extends MvpView {

    void startDetailActivity(String photoUrl);
    void updateRecyclerView();

}
