package com.achers.ascmake.circlefriends;

/**
 * Created on 2019/6/24 10:52
 * <p>
 * author lhm
 * <p>
 * Description:
 * <p>
 * Remarks:
 */

import android.view.ViewGroup;
import android.widget.CompoundButton;

/**
 * 作者:王浩 邮件:bingoogolapple@gmail.com
 * 创建时间:15/10/7 下午8:35
 * 描述:AdapterView和RecyclerView的item中子控件选中状态变化事件监听器
 */
public interface BGAOnItemChildCheckedChangeListener {
    void onItemChildCheckedChanged(ViewGroup parent, CompoundButton childView, int position, boolean isChecked);
}