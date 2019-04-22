package com.achers.ascmake.slidemenu.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import java.util.ArrayList

/**
 * Created on 2019/1/30 18:29
 * <p>
 * author lhm
 * <p>
 * Description:
 * <p>
 * Remarks:
 */
class SimpleViewPagerAdapter(fm: FragmentManager, private val fragmentList: ArrayList<Fragment>) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

}