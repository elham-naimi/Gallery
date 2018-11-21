package com.elna.gallery.ui.activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

import com.bumptech.glide.Glide;
import com.elna.gallery.databinding.ActivityDetailBinding;

import com.elna.gallery.R;
import com.elna.gallery.databinding.ActivityMainBinding;
import com.elna.gallery.utils.Constant;
import com.elna.gallery.viewmodel.DetailActivityViewModel;
import com.elna.gallery.viewmodel.IDetailActivityView;
import com.elna.gallery.viewmodel.IMainActivityView;
import com.elna.gallery.viewmodel.MainActivityViewModel;

public class DetailActivity extends BaseActivity<ActivityDetailBinding, DetailActivityViewModel> implements IDetailActivityView {

    ActivityDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail);
        viewModel = new DetailActivityViewModel(getIntent().getStringExtra(Constant.IMAGE_URL));
        binding.setPhotoDetailViewModel(viewModel);


    }


}
