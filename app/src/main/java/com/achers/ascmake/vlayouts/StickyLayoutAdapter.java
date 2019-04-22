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
 * Created on 2019/2/24 23:03
 * <p>
 * author lhm
 * <p>
 * Description:
 * <p>
 * Remarks:
 */
public class StickyLayoutAdapter extends DelegateAdapter.Adapter<StickyLayoutAdapter.ViewHolder> {

    public Context context;
    private LayoutHelper helper;
    private LayoutInflater inflater;
    private String name;
    private String data;
    private int mViewTypeItem;

    public StickyLayoutAdapter(Context context, LayoutHelper helper, int mViewTypeItem,String url) {
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
    public StickyLayoutAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        return new StickyLayoutAdapter.ViewHolder(inflater.inflate(R.layout.layout_tea_singe_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull StickyLayoutAdapter.ViewHolder viewHolder, int i) {
        viewHolder.title.setText(data);
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    /**
     * 必须重写不然会出现滑动不流畅的情况
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        return 5;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        //        private ImageView url;
        private TextView title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
//            url = itemView.findViewById(R.id.grid_item_iv);
            title = itemView.findViewById(R.id.tv);
        }
    }
}