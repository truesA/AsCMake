package com.achers.ascmake.slidemenu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.achers.ascmake.R;

import java.util.List;


/**
 * 好友列表的数据适配器
 * Created by yangjing on 17-6-8.
 */

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.ViewHolder> {

    private Context context;
    private List<FriendBean> datas;
    private int mSelectedPos =0;//实现单选  方法二，变量保存当前选中的position
    private RecyclerView mRv;//实现单选方法三： RecyclerView另一种定向刷新方法：

    public FriendAdapter(Context context) {
        this.context = context;
    }
    public FriendAdapter(Context context, List<FriendBean> data,RecyclerView rv) {
        this.context = context;
        this.datas=data;
        this.mRv = rv;

        for (int i = 0; i < datas.size(); i++) {
            if (datas.get(i).isSelected()) {
                mSelectedPos = i;
            }
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_friend, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvFriend.setText("friend" + datas.get(position).getName());
        if (datas.get(position).isSelected()){
            holder.bgFriend.setBackgroundColor(context.getResources().getColor(R.color.blue));

        }else {
            holder.bgFriend.setBackgroundColor(context.getResources().getColor(R.color.white));
        }

        holder.cbFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //实现单选方法三： RecyclerView另一种定向刷新方法：不会有白光一闪动画 也不会重复onBindVIewHolder
//                ViewHolder couponVH = (ViewHolder) mRv.findViewHolderForLayoutPosition(mSelectedPos);
//                if (couponVH != null) {//还在屏幕里
//                    couponVH.bgFriend.setBackgroundColor(context.getResources().getColor(R.color.white));
//                } else {//add by 2016 11 22 for 一些极端情况，holder被缓存在Recycler的cacheView里，
//                    //此时拿不到ViewHolder，但是也不会回调onBindViewHolder方法。所以add一个异常处理
//                    notifyItemChanged(mSelectedPos);
//                }
//                datas.get(mSelectedPos).setSelected(false);//不管在不在屏幕里 都需要改变数据
//                //设置新Item的勾选状态
//                mSelectedPos = position;
//                datas.get(mSelectedPos).setSelected(true);
//                holder.bgFriend.setBackgroundColor(context.getResources().getColor(R.color.blue));

                if(datas.get(position).isSelected()){
                    for (FriendBean item :datas){
                        item.setSelected(false);
                    }
                    datas.get(position).setSelected(false);
                    notifyDataSetChanged();
                }else {
                    for (FriendBean item :datas){
                        item.setSelected(false);
                    }
                    datas.get(position).setSelected(true);
                    notifyDataSetChanged();
                }
            }
        });

//
    }

    public void setPosition(int position){
        for (FriendBean item :datas){
            item.setSelected(false);
        }
        datas.get(position).setSelected(true);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvFriend;
        Button cbFriend;
        LinearLayout bgFriend;

        public ViewHolder(View itemView) {
            super(itemView);
            tvFriend = (TextView) itemView.findViewById(R.id.if_tv);
            cbFriend = (Button) itemView.findViewById(R.id.bt);
            bgFriend = (LinearLayout) itemView.findViewById(R.id.bg);
        }
    }
}
