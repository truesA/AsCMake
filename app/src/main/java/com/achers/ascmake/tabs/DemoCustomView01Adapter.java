package com.achers.ascmake.tabs;

/**
 * Created on 2019/6/17 16:08
 * <p>
 * author lhm
 * <p>
 * Description:
 * <p>
 * Remarks:
 */

import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.achers.ascmake.R;
import com.nshmura.recyclertablayout.RecyclerTabLayout;

import java.util.ArrayList;

/**
 * Created by Shinichi Nishimura on 2015/07/22.
 */
public class DemoCustomView01Adapter extends RecyclerTabLayout.Adapter<DemoCustomView01Adapter.ViewHolder> {

    public DemoCustomView01Adapter(ViewPager viewPager) {
        super(viewPager);

    }

    ArrayList<String> list;
    ViewPager viewPage;

    public DemoCustomView01Adapter(ViewPager viewPager, ArrayList<String> list) {
        super(viewPager);
        this.viewPage=viewPager;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate your view.
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_custom_view01_tab, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // Bind data
        holder.tv.setText(list.get(position));

        if (position == getCurrentIndicatorPosition()) {
            //Highlight view
            holder.tv.setTextColor(Color.RED);
        }else {
            holder.tv.setTextColor(Color.BLACK);
        }

        holder.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPage.setCurrentItem(position,false);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv;

        public ViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tvs);
        }
    }
}