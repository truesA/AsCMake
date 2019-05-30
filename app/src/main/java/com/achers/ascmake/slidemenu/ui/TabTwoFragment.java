package com.achers.ascmake.slidemenu.ui;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.achers.ascmake.R;
import com.achers.ascmake.slidemenu.SortScrollbarsView;
import com.achers.ascmake.slidemenu.adapter.FriendAdapter;
import com.achers.ascmake.slidemenu.adapter.FriendBean;
import com.achers.ascmake.slidemenu.adapter.TabTwoAdapter;
import com.achers.ascmake.test.CustomLayoutManager;

import java.util.ArrayList;
import java.util.List;


/**
 * TabTwo
 * Created by yangjing on 17-6-7.
 */

public class TabTwoFragment extends Fragment {

    public static TabTwoFragment newInstance() {
        Bundle args = new Bundle();
        TabTwoFragment fragment = new TabTwoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    //ui
    private Context mContext;
    private GridLayoutManager layoutManager;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frg_tab_two, container, false);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.ff_rv);
        SortScrollbarsView scroll = (SortScrollbarsView) rootView.findViewById(R.id.scroll);
        layoutManager= new GridLayoutManager(mContext, 2, GridLayoutManager.HORIZONTAL, false);

        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setLayoutManager(new CustomLayoutManager(mContext));
        TabTwoAdapter tabTwoAdapter = new TabTwoAdapter(mContext);
        recyclerView.setAdapter(tabTwoAdapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
               Log.e("Scroll", recyclerView.getWidth()+"---");
                View firstVisiableChildView = layoutManager.getChildAt(0);
                offset = offset + dx;
                scroll.changeOffsetX(offset, firstVisiableChildView.getWidth()*5-recyclerView.getWidth());
            }
        });
        recyclerView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

            }
        });
        return rootView;
    }

    private int offset = 0;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
