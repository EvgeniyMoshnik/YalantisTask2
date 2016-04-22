package com.example.evgeniy.yalantistask2.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.evgeniy.yalantistask2.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Evgeniy
 */
public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {
    private Context mContext;
    private List<String> mUrlArray;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImageView;

        public ViewHolder(View v) {
            super(v);
            mImageView = (ImageView) v.findViewById(R.id.imageViewRecycle);
        }
    }


    public ImageAdapter(Context context, List<String> model) {
        mContext = context;
        initModel(model);
    }

    private void initModel(Collection<String> data) {
        mUrlArray = new ArrayList<>(data.size());
        mUrlArray.addAll(data);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_detail, parent, false);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Picasso.with(mContext).load(mUrlArray.get(position)).error(R.drawable.no_photo_available).into(holder.mImageView);
    }


    @Override
    public int getItemCount() {
        return mUrlArray.size();
    }
}
