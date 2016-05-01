package com.example.evgeniy.yalantistask2.adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.evgeniy.yalantistask2.R;
import com.example.evgeniy.yalantistask2.data.AppealEntity;
import com.example.evgeniy.yalantistask2.data.InitData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Evgeniy
 */
public class AppealListAdapter extends BaseAdapter {

    private List<AppealEntity> mModel;
    private Context mContext;

    public AppealListAdapter(Context context, List<AppealEntity> model) {

        mContext = context;
        mModel = new ArrayList<>();

        if (model != null) {
            mModel.addAll(model);
        }
    }

    @Override
    public int getCount() {
        return mModel.size();
    }

    @Override
    public AppealEntity getItem(int position) {
        return mModel.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mModel.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        AppealViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_list_card, parent, false);
            holder = new AppealViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (AppealViewHolder) convertView.getTag();
        }

        AppealEntity appealEntity = mModel.get(position);

        holder.mTvCategoryTitle.setText(appealEntity.getCategory());
        holder.mTvTaskDesc.setText(appealEntity.getFullText());
        holder.mTvLikesAmount.setText(String.valueOf(appealEntity.getLikeAmount()));
        holder.mIvCategoryIcon.setImageDrawable(ContextCompat.getDrawable(mContext, appealEntity.getIconId()));
        holder.mTvDateCreated.setText(InitData.getFormatter().format(appealEntity.getCreated()));
        String days = mContext.getResources().getString(R.string.days);
        holder.mTvDaysAmount.setText(String.valueOf(appealEntity.getDaysAmount()).concat(" ").concat(days));

        return convertView;
    }

    private class AppealViewHolder {

        private TextView mTvCategoryTitle;
        private TextView mTvTaskDesc;
        private TextView mTvDaysAmount;
        private TextView mTvDateCreated;
        private TextView mTvLikesAmount;
        private ImageView mIvCategoryIcon;

        public AppealViewHolder(View itemView) {

            mTvCategoryTitle = (TextView) itemView.findViewById(R.id.category_title);
            mIvCategoryIcon = (ImageView) itemView.findViewById(R.id.category_icon);
            mTvTaskDesc = (TextView) itemView.findViewById(R.id.task_desc);
            mTvDaysAmount = (TextView) itemView.findViewById(R.id.amount_days);
            mTvDateCreated = (TextView) itemView.findViewById(R.id.date_created);
            mTvLikesAmount = (TextView) itemView.findViewById(R.id.likes_amount);

        }
    }
}
