package com.achers.ascmake.vlayouts.vdemo.holder;

import android.view.View;

import com.achers.ascmake.R;
import com.achers.ascmake.vlayouts.vdemo.base.VBaseHolder;
import com.achers.ascmake.vlayouts.vdemo.bean.CommonBean;


/**
 * Created by alphabet on 2018/6/7.
 */

public class LinearHolder extends VBaseHolder<CommonBean> {
  public LinearHolder(View itemView) {
    super(itemView);
  }

  @Override public void setData(int position, CommonBean mData) {
    super.setData(position, mData);
    loadImage(R.id.iv_icon,mData.picUrl);
    setText(R.id.tv_name,mData.name);
  }

  @Override public void init() {
    setClickListener(R.id.iv_icon, new View.OnClickListener() {
      @Override public void onClick(View view) {
        itemChildClickListener.onItemChildClick(get(R.id.iv_icon),position,mData);
      }
    });
    setClickListener(R.id.tv_name, new View.OnClickListener() {
      @Override public void onClick(View view) {
        itemChildClickListener.onItemChildClick(get(R.id.tv_name),position,mData);
      }
    });
  }
}
