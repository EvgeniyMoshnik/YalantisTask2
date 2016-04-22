package com.example.evgeniy.yalantistask2.adapters;

import android.content.Context;
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

        View v = convertView;
        AppealViewHolder holder;

        if (v == null) {
            v = LayoutInflater.from(mContext).inflate(R.layout.item_list_card, parent, false);
            holder = new AppealViewHolder(v);
        } else {
            holder = (AppealViewHolder) v.getTag();
        }

        AppealEntity appealEntity = mModel.get(position);

        holder.categoryTitle.setText(appealEntity.getCategory());
        holder.taskDesc.setText(appealEntity.getFullText());
        holder.likesAmount.setText(String.valueOf(appealEntity.getLikeAmount()));
        holder.categoryIcon.setImageDrawable(mContext.getResources().getDrawable(appealEntity.getIconId()));
        holder.dateCreated.setText(InitData.getFormatter().format(appealEntity.getCreated()));
        String days = mContext.getResources().getString(R.string.days);
        holder.daysAmount.setText(String.valueOf(appealEntity.getDaysAmount()).concat(" ").concat(days));

        v.setTag(holder);

        return v;
    }

    private class AppealViewHolder {

        TextView categoryTitle;
        TextView taskDesc;
        TextView daysAmount;
        TextView dateCreated;
        TextView likesAmount;
        ImageView categoryIcon;

        public AppealViewHolder(View itemView) {

            categoryTitle = (TextView) itemView.findViewById(R.id.category_title);
            categoryIcon = (ImageView) itemView.findViewById(R.id.category_icon);
            taskDesc = (TextView) itemView.findViewById(R.id.task_desc);
            daysAmount = (TextView) itemView.findViewById(R.id.amount_days);
            dateCreated = (TextView) itemView.findViewById(R.id.date_created);
            likesAmount = (TextView) itemView.findViewById(R.id.likes_amount);

        }
    }
}
