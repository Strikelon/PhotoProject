package com.strikalov.photoproject.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public interface DetailView extends MvpView {

    @StateStrategyType(SkipStrategy.class)
    void downloadImage(String photoUrl);

}
