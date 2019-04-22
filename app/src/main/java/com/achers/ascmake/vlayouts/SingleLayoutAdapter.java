package com.achers.ascmake.vlayouts;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.achers.ascmake.R;
import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;

import java.util.ArrayList;

/**
 * Created on 2019/2/24 22:06
 * <p>
 * author lhm
 * <p>
 * Description:
 * <p>
 * Remarks:
 */
public class SingleLayoutAdapter extends DelegateAdapter.Adapter<SingleLayoutAdapter.ViewHolder> {

    public Context context;
    private LayoutHelper helper;
    private LayoutInflater inflater;
    private String name;
//    private ArrayList<String> data;
    private String data;
    private int mViewTypeItem;

    public SingleLayoutAdapter(Context context, LayoutHelper helper, int mViewTypeItem, String url) {
        this.context = context;
        this.helper = helper;
        this.data = url;
        this.inflater = LayoutInflater.from(context);
        this.mViewTypeItem = mViewTypeItem;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return helper;
    }

    @NonNull
    @Override
    public SingleLayoutAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        return new SingleLayoutAdapter.ViewHolder(inflater.inflate(R.layout.layout_tea_singe_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SingleLayoutAdapter.ViewHolder viewHolder, int i) {
        viewHolder.title.setText(data);
    }

    @Override
    public int getItemCount() {
        return mViewTypeItem;
    }

    @Override
    public int getItemViewType(int position) {
        return 4;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv);
        }
    }
}