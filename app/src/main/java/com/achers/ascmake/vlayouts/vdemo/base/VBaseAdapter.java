package com.achers.ascmake.vlayouts.vdemo.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.achers.ascmake.vlayouts.vdemo.event.OnItemChildClickListener;
import com.achers.ascmake.vlayouts.vdemo.event.OnItemClickListener;
import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class VBaseAdapter<T> extends DelegateAdapter.Adapter<VBaseHolder<T>> {
  private Context mContext;
  private int mResLayout;
  private List<T> mDatas;
  private LayoutHelper mLayoutHelper;
  private Class<? extends VBaseHolder> mClazz;
  private OnItemClickListener itemListener;
  private OnItemChildClickListener itemChildListener;
  private int viewType = -1;

  public VBaseAdapter(Context context,int viewType) {
    mContext = context;
    this.viewType = viewType;
    this.mDatas = new ArrayList<T>();
  }

  public VBaseAdapter(Context context,int viewType, List<T> mDatas, int mResLayout,
      Class<? extends VBaseHolder> mClazz, LayoutHelper layoutHelper,
      OnItemClickListener listener) {
    if (mClazz == null) {
      throw new RuntimeException("clazz is null,please check your params !");
    }
    if (mResLayout == 0) {
      throw new RuntimeException("res is null,please check your params !");
    }
    this.viewType = viewType;
    this.mContext = context;
    this.mResLayout = mResLayout;
    this.mLayoutHelper = layoutHelper;
    this.mClazz = mClazz;
    this.itemListener = listener;
    this.mDatas = mDatas;
  }

  /**
   * 多个条目时使用
   */
  public VBaseAdapter setData(List<T> mDatas) {
    this.mDatas = mDatas;
    notifyDataSetChanged();
    return this;
  }

  /**
   * 单个条目或新增条目时使用
   */
  public VBaseAdapter setItem(T mItem) {
    this.mDatas.add(mItem);
    notifyDataSetChanged();
    return this;
  }

  public VBaseAdapter setLayout(int mResLayout) {
    if (mResLayout == 0) {
      throw new RuntimeException("res is null,please check your params !");
    }
    this.mResLayout = mResLayout;
    return this;
  }

  public VBaseAdapter setLayoutHelper(LayoutHelper layoutHelper) {
    this.mLayoutHelper = layoutHelper;
    return this;
  }

  public VBaseAdapter setHolder(Class<? extends VBaseHolder> mClazz) {
    if (mClazz == null) {
      throw new RuntimeException("clazz is null,please check your params !");
    }
    this.mClazz = mClazz;
    return this;
  }

  /**
   * 条目点击
   */
  public VBaseAdapter setOnItemClickListener(OnItemClickListener listener) {
    this.itemListener = listener;
    return this;
  }

  /**
   * 子条目点击
   */
  public VBaseAdapter setOnItemChildClickListener(OnItemChildClickListener listener) {
    this.itemChildListener = listener;
    return this;
  }

  /**
   *  继承elegateAdapter.Adapter后重写方法，告知elegateAdapter.Adapter使用何种布局管理器
   */
  @Override public LayoutHelper onCreateLayoutHelper() {
    return mLayoutHelper;
  }

  public HashMap<Integer, Object> tags = new HashMap<>();

  public VBaseAdapter setTag(int tag, Object mObject) {
    if (mObject != null) {
      tags.put(tag, mObject);
    }
    return this;
  }

  @Override public VBaseHolder<T> onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(mResLayout, parent, false);
    if (tags != null && tags.size() > 0) {
      for (int tag : tags.keySet()) {
        view.setTag(tag, tags.get(tag));
      }
    }
    try {
      Constructor<? extends VBaseHolder> mClazzConstructor = mClazz.getConstructor(View.class);
      if (mClazzConstructor != null) {
        return mClazzConstructor.newInstance(view);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override public void onBindViewHolder(VBaseHolder holder, int position) {
    holder.setOnItemClickListener(itemListener);
    holder.setOnItemChildClickListener(itemChildListener);
    holder.setContext(mContext);
    holder.setData(position, mDatas.get(position));
  }

  @Override public int getItemCount() {
    return mDatas.size();
  }

  @Override public int getItemViewType(int position) {
    return viewType;
  }
}
