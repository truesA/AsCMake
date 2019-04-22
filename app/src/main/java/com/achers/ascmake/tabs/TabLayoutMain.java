package com.achers.ascmake.tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Window;

import com.achers.ascmake.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created on 2019/2/26 18:30
 * <p>
 * author lhm
 * <p>
 * Description:
 * <p>
 * Remarks:
 */
public class TabLayoutMain extends FragmentActivity {
    private List<Fragment> mTabContents = new ArrayList<Fragment>();
    private FragmentPagerAdapter mAdapter;
    private ViewPager mViewPager;
    private List<String> mDatas = Arrays.asList("Tab1", "Tab2", "Tab3", "Tab4");

    private SlidingTabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_tab_main);
        initView();
        initDatas();
        //设置Tab上的标题
        mTabLayout.setData(mDatas);
        mViewPager.setAdapter(mAdapter);
        //设置关联的ViewPager
        mTabLayout.setViewPager(mViewPager, 0);
    }

    private void initDatas() {
        for (String data : mDatas) {
            SimpleFragment fragment = SimpleFragment.newInstance(data);
            mTabContents.add(fragment);
        }

        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return mTabContents.size();
            }

            @Override
            public Fragment getItem(int position) {
                return mTabContents.get(position);
            }
        };
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.id_vp);
        mTabLayout = (SlidingTabLayout) findViewById(R.id.id_indicator);
    }
}