package com.strikalov.photoproject.model.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "photo_table")
public class PhotoRoomEntity {

    @PrimaryKey
    private long id;

    private String photoUrl;

    public PhotoRoomEntity(long id, String photoUrl){
        this.id = id;
        this.photoUrl = photoUrl;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
