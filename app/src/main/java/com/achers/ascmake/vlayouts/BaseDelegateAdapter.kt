//package com.achers.ascmake.vlayouts
//
//import android.content.Context
//import android.support.v7.widget.RecyclerView
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.EditText
//import android.widget.ImageView
//import android.widget.TextView
//import com.achers.ascmake.R
//import com.alibaba.android.vlayout.DelegateAdapter
//import com.alibaba.android.vlayout.LayoutHelper
//
//
//
///**
// * Created on 2019/2/24 17:27
// * <p>
// * author lhm
// * <p>
// * Description:
// * <p>
// * Remarks:
// */
//class BaseDelegateAdapter : DelegateAdapter.Adapter<BaseDelegateAdapter.BaseViewHolder>() {
//
//    private val mLayoutHelper: LayoutHelper? = null
//    private val mCount = -1
//    private val mLayoutId = -1
//    private val mContext: Context? = null
//    private val mViewTypeItem = -1
//
//    /**
//     * 必须重写不然会出现滑动不流畅的情况
//     *
//     * @param position
//     * @return
//     */
//    override fun getItemViewType(position: Int): Int {
//        return mViewTypeItem
//    }
//
//    //条目数量
//    override fun getItemCount(): Int {
//        return mCount
//    }
//
//
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseDelegateAdapter.BaseViewHolder {
//        return if (viewType == mViewTypeItem) {
//            BaseViewHolder(LayoutInflater.from(mContext).inflate(mLayoutId, parent, false))
//        } else null
//    }
//
//   class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//
//   }
//
//
//
//
//}