package com.example.evgeniy.yalantistask2.utils;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.example.evgeniy.yalantistask2.R;
import com.example.evgeniy.yalantistask2.activities.DetailActivity;
import com.example.evgeniy.yalantistask2.adapters.AppealRecyclerAdapter;
import com.example.evgeniy.yalantistask2.data.AppealEntity;

/**
 * Created by Evgeniy
 */
public class InvokerDetail implements AppealRecyclerAdapter.OnItemClickListener, AdapterView.OnItemClickListener {

    private Context mContext;

    public InvokerDetail(Context context) {
        mContext = context;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        AppealEntity entity = (AppealEntity) parent.getAdapter().getItem(position);
        invoke(entity);
    }

    @Override
    public void onItemClick(AppealEntity entity) {
        invoke(entity);
    }

    private void invoke(AppealEntity entity) {
        Intent intent = new Intent(mContext, DetailActivity.class);
        intent.putExtra(mContext.getString(R.string.key_for_entity), entity);
        mContext.startActivity(intent);
    }
}
