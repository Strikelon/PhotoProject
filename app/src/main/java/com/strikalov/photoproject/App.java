package com.strikalov.photoproject;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.crashlytics.android.Crashlytics;
import com.squareup.leakcanary.LeakCanary;
import com.strikalov.photoproject.dagger.AppComponent;
import com.strikalov.photoproject.dagger.AppModule;
import com.strikalov.photoproject.dagger.DaggerAppComponent;
import com.strikalov.photoproject.model.Model;
import com.strikalov.photoproject.model.database.AppDatabase;

import io.fabric.sdk.android.Fabric;


public class App extends Application {

    private static App instance;

    private AppComponent appComponent;


    @Override
    public void onCreate() {
        super.onCreate();

        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);

        Fabric.with(this, new Crashlytics());

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
