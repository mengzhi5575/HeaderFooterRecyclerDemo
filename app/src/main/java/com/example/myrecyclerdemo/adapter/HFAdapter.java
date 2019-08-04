package com.example.myrecyclerdemo.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myrecyclerdemo.R;

import java.util.List;

public class HFAdapter extends BaseAdapter {
    private List<String> mData;

    public HFAdapter(List<String> mData) {
        super(mData);
    }

    @Override
    public RecyclerView.ViewHolder onCreateHolder(ViewGroup viewGroup, int itemType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recler_view_item_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindHolder(RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder) holder).mTextContent.setText(getData().get(position));
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextContent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextContent = itemView.findViewById(R.id.item_text_content);
        }
    }
}
