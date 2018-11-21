package com.elna.gallery.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.elna.gallery.R;
import com.elna.gallery.model.Photo;

import java.util.ArrayList;
import java.util.List;
import com.elna.gallery.databinding.ListItemBinding;
import com.elna.gallery.ui.activities.DetailActivity;
import com.elna.gallery.utils.Constant;

public class PhotoListAdapter extends RecyclerView.Adapter<PhotoListAdapter.PhotoViewHolder> {

    private List<Photo> photos = new ArrayList<Photo>();

    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ListItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.list_item, parent, false);
        return new PhotoViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(PhotoViewHolder holder, int position) {
        holder.update(photos.get(position));

        holder.itemView.setOnClickListener(view -> {
            Intent detailViewIntent = new Intent(view.getContext(), DetailActivity.class);
            detailViewIntent.putExtra(Constant.IMAGE_URL,photos.get(position).getUrl());
            view.getContext().startActivity(detailViewIntent);

        });
    }

    @Override public int getItemCount() {
        return photos.size();
    }


    public void setPhotos(List<Photo> trendsItems) {
        this.photos = trendsItems;
        notifyDataSetChanged();
    }

    public static class PhotoViewHolder extends RecyclerView.ViewHolder {

        ListItemBinding binding;

        public PhotoViewHolder(ListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void update(Photo photo) {
            binding.setPhoto(photo);
            binding.executePendingBindings();
        }
    }
}
