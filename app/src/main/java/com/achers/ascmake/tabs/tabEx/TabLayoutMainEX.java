package com.achers.ascmake.tabs.tabEx;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.achers.ascmake.R;
import com.achers.ascmake.tabs.SimpleFragment;
import com.ruffian.library.RVPIndicator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created on 2019/2/27 10:27
 * <p>
 * author lhm
 * <p>
 * Description:
 * <p>
 * Remarks:
 */
public class TabLayoutMainEX  extends AppCompatActivity {

    private List<Fragment> mTabContents = new ArrayList<Fragment>();
    private FragmentPagerAdapter mAdapter;

    private ViewPager mViewPager;
    private RVPIndicator mIndicator, mIndicator1, mIndicator3, mIndicator4;
    private List<String> mList = Arrays.asList("军事", "政治", "娱乐", "头条", "段子", "视频", "直播");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_ex);

        initView();
        init();

        //设置线性指示器左右Padding
        //  mIndicator.setStyleLinePadding(10);
        //设置title
        //  mIndicator.setTitle("哈哈哈",1);
        //  mIndicator.setTitleList(mList);

        // Indicator选中监听
        mIndicator.setOnIndicatorSelected(new RVPIndicator.OnIndicatorSelected() {

            @Override
            public void setOnIndicatorSelected(int position, String title) {
                Log.w("TAG", "setOnIndicatorSelected");

            }
        });

        // PageChange监听
        mIndicator.setOnPageChangeListener(new RVPIndicator.PageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                Log.w("TAG", "onPageSelected");
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.w("TAG", "onPageScrolled");
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.w("TAG", "onPageScrollStateChanged");
            }
        });
    }

    private void init() {

        for (String data : mList) {
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

        // 设置Tab上的标题
        mIndicator.setTitleList(mList);
        mIndicator1.setTitleList(mList);
        mIndicator3.setTitleList(mList);
        mIndicator4.setTitleList(mList);

        // 设置关联的ViewPager
        mIndicator.setViewPager(mViewPager, 0);

        //设置Adapter
        mViewPager.setAdapter(mAdapter);

    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.vp_content);
        mIndicator = (RVPIndicator) findViewById(R.id.vp_indicator);
        mIndicator1 = (RVPIndicator) findViewById(R.id.vp_indicator1);
        mIndicator3 = (RVPIndicator) findViewById(R.id.vp_indicator3);
        mIndicator4 = (RVPIndicator) findViewById(R.id.vp_indicator4);
    }

}