package com.example.evgeniy.yalantistask2.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.example.evgeniy.yalantistask2.R;
import com.example.evgeniy.yalantistask2.adapters.ImageAdapter;
import com.example.evgeniy.yalantistask2.data.AppealEntity;

import java.text.DateFormat;

/**
 * Created by Evgeniy
 */
public class DetailActivity extends AppCompatActivity {

    private TextView mTextViewValueCategory;
    private TextView mTextViewValueResponsible;
    private TextView mTextViewDescription;
    private TextView mTextViewValueStatus;

    private TextView mTextViewValueCreated;
    private TextView mTextViewValueRegistered;
    private TextView mTextViewValueDeadline;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        mTextViewValueCreated = (TextView) findViewById(R.id.creation_date);
        mTextViewValueRegistered = (TextView) findViewById(R.id.registration_date);
        mTextViewValueDeadline = (TextView) findViewById(R.id.deadline);


        mTextViewValueCategory = (TextView) findViewById(R.id.communal_services);
        mTextViewValueStatus = (TextView) findViewById(R.id.textInWorking);
        mTextViewValueResponsible = (TextView) findViewById(R.id.dnipropetrovsk);
        mTextViewDescription = (TextView) findViewById(R.id.problem_description);


        Intent intent = getIntent();
        AppealEntity entity = (AppealEntity) intent.getSerializableExtra(getString(R.string.key_for_entity));

        if (entity != null) {
            setEntityData(entity, toolbar);
        } else {
            finish();
        }
    }

    private void setEntityData(AppealEntity entity, Toolbar actionBar) {
        if (actionBar != null) {
            actionBar.setTitle(entity.getNumber());
        }

        setRecyclerView(entity);
        initDates(entity);

        mTextViewValueCategory.setText(entity.getCategory());
        mTextViewValueResponsible.setText(entity.getResponsible());
        mTextViewDescription.setText(entity.getFullText());

        switch (entity.getState()) {
            case IN_WORK:
                mTextViewValueStatus.setText(R.string.str_in_work);
                break;
            case DONE:
                mTextViewValueStatus.setText(R.string.str_done);
                break;
            case WAIT:
                mTextViewValueStatus.setText(R.string.str_wait);
                break;
            default:
                mTextViewValueStatus.setText(R.string.emptyString);
                break;
        }
    }

    private void initDates(AppealEntity entity) {

        DateFormat dateFormat = android.text.format.DateFormat.getMediumDateFormat(getApplicationContext());

        mTextViewValueRegistered.setText(dateFormat.format(entity.getRegistered()));
        mTextViewValueCreated.setText(dateFormat.format(entity.getCreated()));
        mTextViewValueDeadline.setText(dateFormat.format(entity.getDeadline()));
    }

    // Initialize RecyclerView
    public void setRecyclerView(AppealEntity entity) {

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        if (mRecyclerView != null) {
            mRecyclerView.setHasFixedSize(true);

            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
            mRecyclerView.setLayoutManager(mLayoutManager);

            RecyclerView.Adapter mAdapter = new ImageAdapter(this, entity.getImages());
            mRecyclerView.setAdapter(mAdapter);
        }

    }

    // Back button close application
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    // onClick for views
    public void toastShow(View v) {
        String toastMessage = v.getClass().getSimpleName();
        Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show();
    }

}
