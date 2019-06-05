package com.strikalov.photoproject.model.network;

import com.strikalov.photoproject.model.entity.PhotoList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PixabayApiService {

    @GET("api")
    Observable<PhotoList> loadPhotoList(@Query("key") String key);

}
