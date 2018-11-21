package com.elna.gallery.ui.activities;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;


import com.elna.gallery.R;
import com.elna.gallery.model.Photo;
import com.elna.gallery.network.ApiFactory;
import com.elna.gallery.ui.adapter.PhotoListAdapter;
import com.elna.gallery.utils.GalleryItemDecoration;
import com.elna.gallery.viewmodel.IMainActivityView;
import com.elna.gallery.viewmodel.MainActivityViewModel;
import com.elna.gallery.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainActivityViewModel> implements IMainActivityView {



    private PhotoListAdapter photosAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        viewModel = new MainActivityViewModel(ApiFactory.create());
        viewModel.attach(this);

        bindView(R.layout.activity_main);

        binding.setIsLoading(true);

        photosAdapter = new PhotoListAdapter();
        binding.recyclerView.setAdapter(photosAdapter);
        binding.recyclerView.setNestedScrollingEnabled(false);
        Log.i("TAG","Oncreate"+getResources().getInteger(R.integer.gallery_columns));
        binding.recyclerView.setLayoutManager(new GridLayoutManager(this,getResources().getInteger(R.integer.gallery_columns)));
        binding.recyclerView.addItemDecoration(new GalleryItemDecoration(
                getResources().getDimensionPixelSize(R.dimen.photos_list_spacing),
                getResources().getInteger(R.integer.gallery_columns)));
    }

    @Override
    protected void onStart() {
        super.onStart();
        viewModel.fetchPhotos();
    }


    @Override
    public void load(List<Photo> items) {
        binding.setIsLoading(false);
        photosAdapter.setPhotos(items);
    }

    @Override
    public void error() {
        super.error();
        binding.setIsLoading(false);
    }

    @Override
    public void error(Throwable e) {
        super.error(e);
        binding.setIsLoading(false);
    }


}
