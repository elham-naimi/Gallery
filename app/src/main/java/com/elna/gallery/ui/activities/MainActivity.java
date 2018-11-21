package com.elna.gallery.ui.activities;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;

import com.elna.gallery.R;
import com.elna.gallery.databinding.ActivityMainBinding;
import com.elna.gallery.model.Photo;
import com.elna.gallery.network.ApiFactory;
import com.elna.gallery.ui.adapter.PhotoListAdapter;
import com.elna.gallery.utils.GalleryItemDecoration;
import com.elna.gallery.viewmodel.IMainActivityView;
import com.elna.gallery.viewmodel.MainActivityViewModel;

import java.util.List;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainActivityViewModel> implements IMainActivityView {



    private PhotoListAdapter photosAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        viewModel = new MainActivityViewModel(ApiFactory.create());
        viewModel.attach(this);

        bindView(R.layout.activity_main);



        photosAdapter = new PhotoListAdapter();
        binding.recyclerView.setAdapter(photosAdapter);

        binding.recyclerView.setLayoutManager(new GridLayoutManager(this,getResources().getInteger(R.integer.gallery_columns)));
        binding.recyclerView.addItemDecoration(new GalleryItemDecoration(
                getResources().getDimensionPixelSize(R.dimen.photos_list_spacing),
                getResources().getInteger(R.integer.gallery_columns)));


        binding.setIsLoading(true);
        initSwipeToRefreshView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        viewModel.fetchPhotos();
    }

    private void initSwipeToRefreshView() {
        binding.swipeRefreshLayout.setEnabled(true);
        binding.swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        Log.i("TAG","called onRefresh");
                        showRefreshProgressBar();
                       viewModel.fetchPhotos();
                    }
                }
        );
    }
    public void showRefreshProgressBar() {
        if (binding.swipeRefreshLayout != null &&
                !binding.swipeRefreshLayout.isRefreshing()) {
            binding.swipeRefreshLayout.post(new Runnable() {
                @Override
                public void run() {
                    binding.swipeRefreshLayout.setRefreshing(true);
                    binding.swipeRefreshLayout.setEnabled(false);
                }
            });
        }
    }

    public void hideRefreshProgressBar() {
        if (binding.swipeRefreshLayout != null &&
                binding.swipeRefreshLayout.isRefreshing()) {
            binding.swipeRefreshLayout.post(new Runnable() {
                @Override
                public void run() {
                    binding.swipeRefreshLayout.setRefreshing(false);
                    binding.swipeRefreshLayout.setEnabled(true);
                }
            });
        }
    }


    @Override
    public void load(List<Photo> items) {
        binding.setIsLoading(false);
        photosAdapter.setPhotos(items);
        hideRefreshProgressBar();

    }

    @Override
    public void error() {
        super.error();
        binding.setIsLoading(false);
        hideRefreshProgressBar();
    }

    @Override
    public void error(Throwable e) {
        super.error(e);
        binding.setIsLoading(false);
        hideRefreshProgressBar();
    }


}
