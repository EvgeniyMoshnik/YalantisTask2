package com.example.evgeniy.yalantistask2.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.evgeniy.yalantistask2.R;
import com.example.evgeniy.yalantistask2.adapters.AppealRecyclerAdapter;
import com.example.evgeniy.yalantistask2.data.AppealEntity;
import com.example.evgeniy.yalantistask2.data.InitData;
import com.example.evgeniy.yalantistask2.data.State;
import com.example.evgeniy.yalantistask2.utils.InvokerDetail;

import com.melnykov.fab.FloatingActionButton;

import java.util.List;


/**
 * Created by Evgeniy
 */
public class AppealRecyclerFragment extends Fragment {

    public static final String STATE_KEY = "state";

    private AppealRecyclerAdapter mAdapter;

    public static Fragment getInstance(State state) {

        Fragment fragment = new AppealRecyclerFragment();

        Bundle bundle = new Bundle();
        bundle.putInt(STATE_KEY, state.getValue());

        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        State state = State.WAIT;
        Bundle params = getArguments();
        if (params != null) {
            state = State.getByValue(params.getInt(STATE_KEY, -1));
        }

        List<AppealEntity> appealList = InitData.getModel(getContext(), state);
        mAdapter = new AppealRecyclerAdapter(getContext(), appealList, new InvokerDetail(getContext()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.recycler_view, container, false);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyclerView.setAdapter(mAdapter);

        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        fab.attachToRecyclerView(recyclerView);

        return recyclerView;
    }

}
