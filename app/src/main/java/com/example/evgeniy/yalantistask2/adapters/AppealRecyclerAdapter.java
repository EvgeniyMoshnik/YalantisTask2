package com.example.evgeniy.yalantistask2.adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.evgeniy.yalantistask2.R;
import com.example.evgeniy.yalantistask2.data.AppealEntity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

/**
 * Created by Evgeniy
 */
public class AppealRecyclerAdapter extends RecyclerView.Adapter<AppealRecyclerAdapter.AppealViewHolder> {

    private Context mContext;
    private List<AppealEntity> mModel;

    private OnItemClickListener mOnItemClickListener;
    private DateFormat mFormatter = new SimpleDateFormat("MMM d, yyyy", Locale.getDefault());

    public AppealRecyclerAdapter(Context context, List<AppealEntity> model, OnItemClickListener listener) {
        mContext = context;
        initModel(model);
        mOnItemClickListener = listener;
    }

    private void initModel(Collection<AppealEntity> data) {
        mModel = new ArrayList<>(data.size());
        mModel.addAll(data);
    }

    public class AppealViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mTvCategoryTitle;
        private TextView mTvTaskDesc;
        private TextView mTvDaysAmount;
        private TextView mTvDateCreated;
        private TextView mTvLikesAmount;
        private ImageView mIvCategoryIcon;

        public AppealViewHolder(View itemView) {
            super(itemView);

            mTvCategoryTitle = (TextView) itemView.findViewById(R.id.category_title);
            mIvCategoryIcon = (ImageView) itemView.findViewById(R.id.category_icon);
            mTvTaskDesc = (TextView) itemView.findViewById(R.id.task_desc);
            mTvDaysAmount = (TextView) itemView.findViewById(R.id.amount_days);
            mTvDateCreated = (TextView) itemView.findViewById(R.id.date_created);
            mTvLikesAmount = (TextView) itemView.findViewById(R.id.likes_amount);

            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();

            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(mModel.get(position));
            }
        }
    }

    @Override
    public AppealViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_list_card, parent, false);
        return new AppealViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AppealViewHolder holder, int position) {

        AppealEntity appealEntity = mModel.get(position);

        holder.mTvCategoryTitle.setText(appealEntity.getCategory());
        holder.mTvTaskDesc.setText(appealEntity.getFullText());
        holder.mTvLikesAmount.setText(String.valueOf(appealEntity.getLikeAmount()));
        holder.mIvCategoryIcon.setImageDrawable(ContextCompat.getDrawable(mContext, appealEntity.getIconId()));
        holder.mTvDateCreated.setText(mFormatter.format(appealEntity.getCreated()));

        String days = mContext.getResources().getString(R.string.days);

        holder.mTvDaysAmount.setText(String.valueOf(appealEntity.getDaysAmount()).concat(" ").concat(days));

    }

    @Override
    public int getItemCount() {
        return mModel.size();
    }

    public interface OnItemClickListener {
        void onItemClick(AppealEntity entity);
    }
}
