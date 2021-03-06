package com.achers.ascmake.vlayouts;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.achers.ascmake.vlayouts.callback.OnItemClickListener;
import com.achers.ascmake.vlayouts.callback.OnItemLongClickListener;


/**
 * Created on 2016/9/30 10:53
 * <p>
 * author wang
 * <p>
 * Description:
 */

public class CommonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {


    private final Context context;
    private final OnItemClickListener clickListener;
    private final OnItemLongClickListener onItemLongClickListener;

    private View itemView;
    private SparseArray<View> mViews;

    public CommonViewHolder(Context context, View itemView, ViewGroup parent, OnItemClickListener clickListener, OnItemLongClickListener onItemLongClickListener) {
        super(itemView);
        this.context = context;
        this.itemView = itemView;
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
        this.clickListener = clickListener;
        this.onItemLongClickListener = onItemLongClickListener;
        mViews = new SparseArray<>();
    }

    public static CommonViewHolder get(Context context, ViewGroup parent, int layoutId, OnItemClickListener clickListener, OnItemLongClickListener onItemLongClickListener) {
        View itemView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        CommonViewHolder holder = new CommonViewHolder(context, itemView, parent, clickListener, onItemLongClickListener);
        return holder;
    }

    /**
     * 通过viewId获取控件
     *
     * @param viewId
     * @return
     */
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public View getItemView() {
        return itemView;
    }

    @Override
    public void onClick(View v) {
        if (clickListener != null) {
            clickListener.onItemClick(v, getAdapterPosition());
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if (onItemLongClickListener != null) {
            onItemLongClickListener.onItemLongClick(v, getAdapterPosition());
        }
        return true;
    }

}
