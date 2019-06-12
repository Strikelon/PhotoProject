package com.strikalov.photoproject.presenters;

import com.strikalov.photoproject.model.Model;
import com.strikalov.photoproject.model.entity.Photo;
import com.strikalov.photoproject.model.entity.PhotoList;
import com.strikalov.photoproject.presenter.IRecyclerMainPresenter;
import com.strikalov.photoproject.presenter.MainPresenter;
import com.strikalov.photoproject.view.MainView;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.schedulers.Schedulers;

@RunWith(MockitoJUnitRunner.class)
public class MainPresenterTest {

    private MainPresenter mainPresenter;
    private MainPresenter spyMainPresenter;

    @Mock
    MainView mainView;

    @Mock
    Model model;

    @BeforeClass
    public static void setupClass() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(
                __ -> Schedulers.trampoline());
    }

    @Before
    public void before(){
        mainPresenter = new MainPresenter(model);
        spyMainPresenter = Mockito.spy(mainPresenter);
    }

    @Test
    public void downloadPhotoListTest(){
        PhotoList photoList = new PhotoList();
        Mockito.when(model.loadPhotoListFromServer()).thenReturn(Observable.just(photoList));

        mainPresenter.attachView(mainView);
        Mockito.verify(mainView).updateRecyclerView();
    }

    @Test
    public void savePhotoListInDatabaseTest(){
        PhotoList photoList = new PhotoList();
        List<Photo> photos = new ArrayList<>();
        Photo photo1 = new Photo();
        photo1.setWebformatURL("test_url");
        Photo photo2 = new Photo();
        photo2.setWebformatURL("test_url");
        photoList.setPhotoList(photos);

        Mockito.when(model.loadPhotoListFromServer()).thenReturn(Observable.just(photoList));

        spyMainPresenter.attachView(mainView);
        Mockito.verify(spyMainPresenter).savePhotoListInDatabase(photos);
    }

    @Test
    public void onRecyclerItemClickTest(){

        int positionItem = 2;
        PhotoList photoList = new PhotoList();

        Mockito.when(model.loadPhotoListFromServer()).thenReturn(Observable.just(photoList));
        IRecyclerMainPresenter recyclerMainPresenter =  mainPresenter.getRecyclerMainPresenter();
        recyclerMainPresenter.onRecyclerItemClick(positionItem);

        mainPresenter.attachView(mainView);
        Mockito.verify(mainView).startDetailActivity(positionItem);
    }

}
