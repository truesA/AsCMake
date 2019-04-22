package com.achers.ascmake.vlayouts;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.achers.ascmake.R;
import com.achers.ascmake.tabs.SimpleFragment;
import com.achers.ascmake.tabs.SlidingTabLayout;
import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created on 2019/3/20 19:03
 * <p>
 * author lhm
 * <p>
 * Description:
 * <p>
 * Remarks:
 */
public class ViewPagerAdapter extends DelegateAdapter.Adapter<ViewPagerAdapter.ViewHolder> {

    public Context context;
    private LayoutHelper helper;
    private LayoutInflater inflater;
    private String name;
//    private ArrayList<String> data;
    //    private String data;
        private FragmentPagerAdapter mAdapter;
    private int mViewTypeItem;
    private List<Fragment> mTabContents = new ArrayList<Fragment>();
    private List<String> mDatas = Arrays.asList("Tab1", "Tab2", "Tab3", "Tab4");

    public ViewPagerAdapter(Context context, LayoutHelper helper, int mViewTypeItem,  FragmentPagerAdapter mAdapters) {
        this.context = context;
        this.helper = helper;
//        this.data = datas;
        this.mAdapter = mAdapters;
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
        return new ViewPagerAdapter.ViewHolder(inflater.inflate(R.layout.layout_viewpage_singe_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder parent, int i) {
        parent.tab.setData(mDatas);
        parent.vp.setAdapter(mAdapter);
        parent.tab.setViewPager( parent.vp,0);
    }

    @Override
    public int getItemCount() {
        return 1;
    }




    class ViewHolder extends RecyclerView.ViewHolder {

        //        private ImageView url;
        private ViewPager vp;
        private SlidingTabLayout tab;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
//            url = itemView.findViewById(R.id.grid_item_iv);
            vp = itemView.findViewById(R.id.id_vp);
            tab = itemView.findViewById(R.id.id_indicator);
        }
    }
}
