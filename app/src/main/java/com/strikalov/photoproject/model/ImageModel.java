package com.strikalov.photoproject.model;

import java.util.ArrayList;
import java.util.List;

public class ImageModel {

    private List<Boolean> list;
    private int clickCount;


    public ImageModel(){
        list = new ArrayList<>();

        for(int i=0; i<10; i++){
            list.add(true);
        }

    }

    public List<Boolean> getList(){
        return list;
    }

    public int incClickCount(){
        return ++clickCount;
    }

}
