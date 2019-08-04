package com.example.myrecyclerdemo.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public abstract class BaseAdapter extends RecyclerView.Adapter {
    private static final int TYPE_NORMAL = 0x11111;
    private static final int TYPE_HEADER = 0x11112;
    private static final int TYPE_FOOTER = 0x11113;

    private View mHeaderView;
    private View mFooterView;
    private List<String> mData;

    public BaseAdapter(List<String> mData) {
        this.mData = mData;
    }

    public List<String> getData() {
        return mData;
    }

    public void setData(List<String> mData) {
        this.mData = mData;
    }

    public View getHeaderView() {
        return mHeaderView;
    }

    public void setHeaderView(View mHeaderView) {
        this.mHeaderView = mHeaderView;
    }

    public View getFooterView() {
        return mFooterView;
    }

    public void setFooterView(View mFooterView) {
        this.mFooterView = mFooterView;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        if (viewType == TYPE_HEADER) {
            return new ViewHolder(mHeaderView);
        }
        if (viewType == TYPE_FOOTER) {
            return new ViewHolder(mFooterView);
        }
        return onCreateHolder(viewGroup, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {

        if (getItemViewType(position) == TYPE_HEADER || getItemViewType(position) == TYPE_FOOTER) {
            return;
        }
        if (mHeaderView != null) {
            onBindHolder(viewHolder, position - 1);
        } else {
            onBindHolder(viewHolder, position);
        }
//        if (mHeaderView != null && mFooterView != null) {
//            if (position == 0) return;
//            else if (position == getItemCount() - 1) return;
//            else mBaseAdapter.onBindViewHolder(viewHolder, position - 1);
//        } else if (mHeaderView != null) {
//            if (position == 0) return;
//            else mBaseAdapter.onBindViewHolder(viewHolder, position - 1);
//        } else if (mFooterView != null) {
//            if (position == getItemCount() - 1) {
//                return;
//            } else {
//                Log.d("ymz", "viewHolder = " + viewHolder);
//                mBaseAdapter.onBindViewHolder(viewHolder, position);
//            }
//        } else {
//            mBaseAdapter.onBindViewHolder(viewHolder, position);
//        }
    }

    @Override
    public int getItemCount() {
        if (mHeaderView != null && mFooterView != null) {
            return mData.size() + 2;
        }
        return (mHeaderView == null && mFooterView == null) ? mData.size() : mData.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (mHeaderView == null && mFooterView == null) {
            return TYPE_NORMAL;
        }
        if (position == 0 && mHeaderView != null) {
            return TYPE_HEADER;
        }
        if ((position == (getItemCount() - 1)) && mFooterView != null) {
            return TYPE_FOOTER;
        }
        return TYPE_NORMAL;
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        final RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            ((GridLayoutManager) layoutManager).setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if (getItemViewType(position) == TYPE_HEADER || getItemViewType(position) == TYPE_FOOTER) {
                        return ((GridLayoutManager) layoutManager).getSpanCount();
                    } else {
                        return 1;
                    }
                }
            });
        }
    }

    public abstract RecyclerView.ViewHolder onCreateHolder(ViewGroup viewGroup, int itemType);

    public abstract void onBindHolder(RecyclerView.ViewHolder holder, int position);

    private class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
