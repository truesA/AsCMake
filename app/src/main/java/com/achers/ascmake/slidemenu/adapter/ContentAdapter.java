package com.achers.ascmake.slidemenu.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.achers.ascmake.slidemenu.ui.TabOneFragment;
import com.achers.ascmake.slidemenu.ui.TabTwoFragment;


/**
 * 右滑菜单适配器
 * Created by yangjing on 17-6-7.
 */

public class ContentAdapter extends FragmentPagerAdapter {

    private String[] menuNames = new String[]{
            "One", "Two"
    };

    public ContentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return TabOneFragment.newInstance();
        } else {
            return TabTwoFragment.newInstance();
        }
    }

    @Override
    public int getCount() {
        return menuNames.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return menuNames[position];
    }
}
