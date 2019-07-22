package com.achers.ascmake.circlefriends;

/**
 * Created on 2019/6/24 11:06
 * <p>
 * author lhm
 * <p>
 * Description:
 * <p>
 * Remarks:
 */

import android.view.View;
import android.view.ViewGroup;

/**
 * 作者:王浩 邮件:bingoogolapple@gmail.com
 * 创建时间:15/5/28 上午7:28
 * 描述:RecyclerView的item点击事件监听器
 */
public interface BGAOnRVItemClickListener {
    void onRVItemClick(ViewGroup parent, View itemView, int position);
}