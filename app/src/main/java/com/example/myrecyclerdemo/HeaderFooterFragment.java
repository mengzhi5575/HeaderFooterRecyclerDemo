package com.example.myrecyclerdemo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myrecyclerdemo.adapter.HFAdapter;

import java.util.ArrayList;
import java.util.List;

public class HeaderFooterFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private List<String> mData;
    private HFAdapter mHFAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.header_footer_fragment, container, false);
        mRecyclerView = view.findViewById(R.id.recyclerview);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mData = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            mData.add(i + "");
        }
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3, GridLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        View header = LayoutInflater.from(getContext()).inflate(R.layout.header_view, mRecyclerView, false);
        View footer = LayoutInflater.from(getContext()).inflate(R.layout.footer_view, mRecyclerView, false);
        mHFAdapter = new HFAdapter(mData);
        mHFAdapter.setHeaderView(header);
        mHFAdapter.setFooterView(footer);
        mRecyclerView.setAdapter(mHFAdapter);
    }
}
