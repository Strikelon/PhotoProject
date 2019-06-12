package com.strikalov.photoproject.presenters;

import com.arellomobile.mvp.DefaultView;
import com.strikalov.photoproject.model.Model;
import com.strikalov.photoproject.model.database.PhotoRoomEntity;
import com.strikalov.photoproject.presenter.DetailPresenter;
import com.strikalov.photoproject.view.DetailView;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Maybe;
import io.reactivex.MaybeObserver;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.schedulers.Schedulers;

@RunWith(MockitoJUnitRunner.class)
public class DetailPresenterTest {

    private DetailPresenter detailPresenter;

    @Mock
    DetailView detailView;

    @Mock
    Model model;

    @BeforeClass
    public static void setupClass() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(
                __ -> Schedulers.trampoline());
    }

    @Before
    public void before(){
        detailPresenter = new DetailPresenter(model);
    }

    @Test
    public void onGetPhotoId(){

        int testId = 1;
        String testUrl = "testUrl";
        PhotoRoomEntity photoRoomEntity = new PhotoRoomEntity(testId, testUrl);

        Mockito.when(model.getPhotoByIdFromDatabase(testId)).thenReturn(Maybe.just(photoRoomEntity));

        detailPresenter.attachView(detailView);
        detailPresenter.onGetPhotoId(testId);

        Mockito.verify(detailView).downloadImage(testUrl);
    }

}
