package com.strikalov.photoproject.model;

import com.strikalov.photoproject.App;
import com.strikalov.photoproject.model.database.AppDatabase;
import com.strikalov.photoproject.model.database.PhotoRoomDao;
import com.strikalov.photoproject.model.database.PhotoRoomEntity;
import com.strikalov.photoproject.model.entity.Photo;
import com.strikalov.photoproject.model.entity.PhotoList;
import com.strikalov.photoproject.model.network.PixabayApi;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class Model {

    private PixabayApi pixabayApi;
    private AppDatabase appDatabase;

    public Model(PixabayApi pixabayApi, AppDatabase appDatabase){
        this.pixabayApi = pixabayApi;
        this.appDatabase = appDatabase;
    }

    public Observable<PhotoList> loadPhotoListFromServer(){
        return pixabayApi.loadPhotoList();
    }

    public Completable insertPhotoInDatabase(int id, String photoUrl){

        PhotoRoomEntity photoRoomEntity = new PhotoRoomEntity(id, photoUrl);

        return Completable.fromCallable(
                () -> appDatabase.photoRoomDao().insertPhoto(photoRoomEntity)
        ).subscribeOn(Schedulers.io());

    }

    public Completable insertPhotoListInDatabase(List<Photo> photoList){

        List<PhotoRoomEntity>  photoRoomEntityList = createPhotoRoomEntityList(photoList);

        return Completable.fromCallable(
                () -> appDatabase.photoRoomDao().insertPhotoList(photoRoomEntityList)
        ).subscribeOn(Schedulers.io());
    }

    private List<PhotoRoomEntity> createPhotoRoomEntityList(List<Photo> photoList){

        List<PhotoRoomEntity>  photoRoomEntityList = new ArrayList<>();

        for(int i=0; i<photoList.size(); i++){
            photoRoomEntityList.add(new PhotoRoomEntity(i, photoList.get(i).getWebformatURL()));
        }

        return photoRoomEntityList;
    }

    public Maybe<List<PhotoRoomEntity>> getAllPhotosFromDatabase(){

        return appDatabase.photoRoomDao().getAllPhotos()
                .subscribeOn(Schedulers.io());

    }

    public Maybe<PhotoRoomEntity> getPhotoByIdFromDatabase(long photoId){

        return appDatabase.photoRoomDao().getPhotoById(photoId)
                .subscribeOn(Schedulers.io());

    }

}
