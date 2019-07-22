package com.achers.ascmake.circlefriends;

/**
 * Created on 2019/6/24 10:53
 * <p>
 * author lhm
 * <p>
 * Description:
 * <p>
 * Remarks:
 */
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 作者:王浩 邮件:bingoogolapple@gmail.com
 * 创建时间:15/5/21 上午1:00
 * 描述:适用于AdapterView的item的ViewHolder
 */
public class BGAAdapterViewHolder {
    protected View mConvertView;
    protected BGAViewHolderHelper mViewHolderHelper;

    private BGAAdapterViewHolder(ViewGroup parent, int layoutId) {
        mConvertView = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        mConvertView.setTag(this);
        mViewHolderHelper = new BGAViewHolderHelper(parent, mConvertView);
    }

    /**
     * 拿到一个可重用的ViewHolder对象
     *
     * @param convertView
     * @param parent
     * @param layoutId
     * @return
     */
    public static BGAAdapterViewHolder dequeueReusableAdapterViewHolder(View convertView, ViewGroup parent, int layoutId) {
        if (convertView == null) {
            return new BGAAdapterViewHolder(parent, layoutId);
        }
        return (BGAAdapterViewHolder) convertView.getTag();
    }

    public BGAViewHolderHelper getViewHolderHelper() {
        return mViewHolderHelper;
    }

    public View getConvertView() {
        return mConvertView;
    }

}