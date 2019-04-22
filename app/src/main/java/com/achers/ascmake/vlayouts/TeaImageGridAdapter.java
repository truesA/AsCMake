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
 * Created on 2019/2/24 21:39
 * <p>
 * author lhm
 * <p>
 * Description:
 * <p>
 * Remarks:
 */
public class TeaImageGridAdapter extends DelegateAdapter.Adapter<TeaImageGridAdapter.ViewHolder> {

    public Context context;
    private LayoutHelper helper;
    private LayoutInflater inflater;
    private String name;
    private ArrayList<String> data;
    private int mViewTypeItem;

    public TeaImageGridAdapter(Context context, LayoutHelper helper, int mViewTypeItem, ArrayList<String> url) {
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
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        return new ViewHolder(inflater.inflate(R.layout.layout_tea_grid_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.title.setText(data.get(i));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    /**
     * 必须重写不然会出现滑动不流畅的情况
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        return 3;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView url;
        private TextView title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            url = itemView.findViewById(R.id.grid_item_iv);
            title = itemView.findViewById(R.id.grid_item_tv);
        }
    }
//    class ViewHolderRight extends RecyclerView.ViewHolder {
//
//        private ImageView url;
//        private TextView title;
//
//        public ViewHolderRight(@NonNull View itemView) {
//            super(itemView);
//            url = itemView.findViewById(R.id.grid_item_iv);
//            title = itemView.findViewById(R.id.grid_item_tv);
//        }
//    }
//    class ViewHolderLeft extends RecyclerView.ViewHolder {
//
//        private ImageView url;
//        private TextView title;
//
//        public ViewHolderLeft(@NonNull View itemView) {
//            super(itemView);
//            url = itemView.findViewById(R.id.grid_item_iv);
//            title = itemView.findViewById(R.id.grid_item_tv);
//        }
//    }


}
