package com.elna.gallery.viewmodel;


import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class DetailActivityViewModel extends BaseViewModel<IDetailActivityView> {
    private String url;
    public DetailActivityViewModel(String url){
        this.url = url;
    }
    public String getThumbnailUrl() {
        return url;
    }

    // Loading Image using Glide Library.
    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url){
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }

}


