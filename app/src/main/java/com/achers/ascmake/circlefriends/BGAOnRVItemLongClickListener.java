package com.achers.ascmake.circlefriends;

/**
 * Created on 2019/6/24 11:07
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
 * 描述:RecyclerView的item长按事件监听器
 */
public interface BGAOnRVItemLongClickListener {
    boolean onRVItemLongClick(ViewGroup parent, View itemView, int position);
}