package com.strikalov.photoproject.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public interface MainView extends MvpView {

    @StateStrategyType(SkipStrategy.class)
    void startDetailActivity(int position);

    @StateStrategyType(SkipStrategy.class)
    void updateRecyclerView();

}
