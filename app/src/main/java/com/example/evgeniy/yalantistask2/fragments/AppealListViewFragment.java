package com.example.evgeniy.yalantistask2.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.ListViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListAdapter;

import com.example.evgeniy.yalantistask2.R;
import com.example.evgeniy.yalantistask2.activities.MainActivity;
import com.example.evgeniy.yalantistask2.adapters.AppealListAdapter;
import com.example.evgeniy.yalantistask2.data.AppealEntity;
import com.example.evgeniy.yalantistask2.data.InitData;
import com.example.evgeniy.yalantistask2.data.State;
import com.example.evgeniy.yalantistask2.utils.InvokerDetail;

import java.util.List;


/**
 * Created by Evgeniy
 */
public class AppealListViewFragment extends Fragment {

    public static final String STATE_KEY = "state";

    private ListAdapter mAdapter;
    private InvokerDetail mInvoker;
    private FloatingActionButton mFab;

    public static Fragment getInstance(State state) {

        Fragment fragment = new AppealListViewFragment();

        Bundle params = new Bundle();
        params.putInt(STATE_KEY, state.getValue());

        fragment.setArguments(params);

        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
            mFab = ((MainActivity) context).getFloatingActionButton();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        State state = State.WAIT;
        Bundle params = getArguments();
        if (params != null) {
            state = State.getByValue(params.getInt(STATE_KEY, -1));
        }

        List<AppealEntity> data = InitData.getModel(getContext(), state);

        mAdapter = new AppealListAdapter(getContext(), data);
        mInvoker = new InvokerDetail(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_list, container, false);

        ListViewCompat listView = (ListViewCompat) v.findViewById(R.id.list_view);
        listView.setAdapter(mAdapter);

        listView.setOnItemClickListener(mInvoker);

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            int lastFirstVisibleItem = 0;

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                int visibility = mFab.getVisibility();
                if (firstVisibleItem > lastFirstVisibleItem && visibility == View.VISIBLE) {
                    mFab.hide();
                } else if (firstVisibleItem < lastFirstVisibleItem && visibility == View.GONE) {
                    mFab.show();
                }
                lastFirstVisibleItem = firstVisibleItem;
            }
        });
        return v;
    }
}
