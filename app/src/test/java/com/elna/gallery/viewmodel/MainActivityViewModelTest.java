package com.elna.gallery.viewmodel;

import com.elna.gallery.model.Photo;
import com.elna.gallery.network.PhotosApi;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class MainActivityViewModelTest {

    @Mock
    IMainActivityView mainActivityView;
    @Mock
    PhotosApi photosApi;
    @ClassRule
    public static final RxSchedulersOverrideRule rxSchedulersOverrideRule = new RxSchedulersOverrideRule();


    private MainActivityViewModel mainActivityViewModel;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mainActivityViewModel = new MainActivityViewModel(photosApi);
        mainActivityViewModel.attach(mainActivityView);
    }

    @Test
    public void fetchPhotos() {
        List<Photo> photoList = new ArrayList<Photo>();
        photoList.add(new Photo());
        doReturn(Observable.just(photoList)).when(photosApi).getPhotos();
        mainActivityViewModel.fetchPhotos();
        verify(mainActivityView,times(1)).load(photoList);
        }

    @Test public void error_fetchPhotos() {
        Throwable throwable = new Throwable();
        doReturn(Observable.error(throwable)).when(photosApi).getPhotos();
        mainActivityViewModel.fetchPhotos();
        verify(mainActivityView,times(1)).error(throwable);
    }
}