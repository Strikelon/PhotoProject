package com.strikalov.photoproject.model.network;

import com.strikalov.photoproject.model.entity.PhotoList;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class PixabayApi {

    private static final String URL_REQUEST = "https://pixabay.com/";
    private static final String API_KEY = "12665161-7ee7dbb37cde2729316d816b7";

    private PixabayApiService pixabayApiService;

    public PixabayApi() {

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(URL_REQUEST)
                .build();

        this.pixabayApiService = retrofit.create(PixabayApiService.class);

    }

    public Observable<PhotoList> loadPhotoList() {

        return pixabayApiService.loadPhotoList(API_KEY).subscribeOn(Schedulers.io());

    }
}
