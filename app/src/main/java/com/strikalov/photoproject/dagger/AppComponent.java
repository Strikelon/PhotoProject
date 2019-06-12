package com.strikalov.photoproject.dagger;

import com.strikalov.photoproject.view.activities.DetailActivity;
import com.strikalov.photoproject.view.activities.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    void injectMainActivity(MainActivity mainActivity);
    void injectDetailActivity(DetailActivity detailActivity);

}
