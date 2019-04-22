package com.achers.ascmake.vlayouts.vdemo.event;

import android.view.View;

/**
 * 条目点击
 */
public interface OnItemClickListener<T> {
    void onItemClick(View view, int position, T mData);
}
