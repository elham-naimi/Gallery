package com.elna.gallery.viewmodel;

import android.databinding.Bindable;
import android.util.Log;

import com.elna.gallery.network.PhotosApi;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * ViewModel for MainActivity
 */

public class MainActivityViewModel extends BaseViewModel<IMainActivityView> {

    private PhotosApi photosApi;


    public MainActivityViewModel(PhotosApi photosApi) {
        this.photosApi = photosApi;
    }


    public void fetchPhotos() {
     compositeDisposable.add(photosApi.getPhotos()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(photoListResponse -> view.load(photoListResponse),
                        throwable -> view.error(throwable)
                ));
    };



}
