package com.strikalov.photoproject.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(SkipStrategy.class)
public interface MainView extends MvpView {

    void startDetailActivity(int position);

    void updateRecyclerView();

}
