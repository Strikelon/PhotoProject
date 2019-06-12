package com.strikalov.photoproject;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.strikalov.photoproject.dagger.AppComponent;
import com.strikalov.photoproject.dagger.AppModule;
import com.strikalov.photoproject.dagger.DaggerAppComponent;
import com.strikalov.photoproject.model.Model;
import com.strikalov.photoproject.model.database.AppDatabase;

public class App extends Application {

    private static App instance;

    private AppComponent appComponent;


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        AppDatabase appDatabase = Room.databaseBuilder(this, AppDatabase.class, "database").build();

        appComponent = DaggerAppComponent.builder().appModule(new AppModule(appDatabase)).build();

    }

    public static App getInstance() {
        return instance;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
