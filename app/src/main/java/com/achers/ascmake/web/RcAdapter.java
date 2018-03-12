package com.achers.ascmake.web;

import android.content.Context;
import android.widget.TextView;

import com.achers.ascmake.CommonRecyclerViewAdapter;
import com.achers.ascmake.CommonViewHolder;
import com.achers.ascmake.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Create on 2018/1/31 15:12
 * <p>
 * author lhm
 * <p>
 * Description:
 * <p>
 * Version: 1.2.3
 */
public class RcAdapter extends CommonRecyclerViewAdapter<String> {
    public RcAdapter(Context context,  ArrayList<String> data) {
        super(context, R.layout.item_s, data);
    }

    @Override
    public void convert(CommonViewHolder holder, String s, int position) {
        TextView textView =holder.getView(R.id.tv);
        textView.setText(s);
    }
}
