package com.elna.gallery.ui.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.elna.gallery.R;
import com.elna.gallery.databinding.ActivityDetailBinding;
import com.elna.gallery.utils.Constant;
import com.elna.gallery.viewmodel.DetailActivityViewModel;
import com.elna.gallery.viewmodel.IDetailActivityView;

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
