package com.strikalov.photoproject.model.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Maybe;

@Dao
public interface PhotoRoomDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertPhoto(PhotoRoomEntity photo);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long[] insertPhotoList(List<PhotoRoomEntity> photoList);

    @Query("SELECT * FROM photo_table")
    Maybe<List<PhotoRoomEntity>> getAllPhotos();

    @Query("SELECT * FROM photo_table WHERE id = :photoId")
    Maybe<PhotoRoomEntity> getPhotoById(long photoId);

}
