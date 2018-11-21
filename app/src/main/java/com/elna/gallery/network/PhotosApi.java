package com.elna.gallery.network;

import com.elna.gallery.model.Photo;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface PhotosApi {
    @GET("/photos")
    Observable<List<Photo>> getPhotos();
}
