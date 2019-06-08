package com.strikalov.photoproject;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.strikalov.photoproject.model.Model;
import com.strikalov.photoproject.model.database.AppDatabase;

public class App extends Application {

    private static App instance;

    private AppDatabase appDatabase;

    private Model model;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        appDatabase = Room.databaseBuilder(this, AppDatabase.class, "database").build();

        model = new Model();
    }

    public static App getInstance() {
        return instance;
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }

    public Model getModel() {
        return model;
    }
}
