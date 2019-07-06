package com.strikalov.photoproject.dagger;

import com.strikalov.photoproject.model.Model;
import com.strikalov.photoproject.model.database.AppDatabase;
import com.strikalov.photoproject.model.network.PixabayApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private AppDatabase appDatabase;

    public AppModule(AppDatabase appDatabase) {
        this.appDatabase = appDatabase;
    }

    @Provides
    @Singleton
    public AppDatabase provideAppDatabase() {
        return appDatabase;
    }

    @Provides
    @Singleton
    public PixabayApi providePixabayApi() {
        return new PixabayApi();
    }

    @Provides
    @Singleton
    public Model provideModel(PixabayApi pixabayApi, AppDatabase appDatabase) {
        return new Model(pixabayApi, appDatabase);
    }

}
