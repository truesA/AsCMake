package com.achers.ascmake;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Create on 2018/2/2 14:58
 * <p>
 * author lhm
 * <p>
 * Description:
 * <p>
 * Version: 1.2.3
 */
public class RcTxAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private ArrayList<RcTx> data;
    private Context context;


    public RcTxAdapter(Context context,ArrayList<RcTx> data){
        this.context=context;
        this.data=data;
    }

    public enum Item_Type{
        RED,BLUE
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType==Item_Type.RED.ordinal()){
          View view =  LayoutInflater.from(context).inflate(R.layout.red_item,null);
            ViewHolderA viewHolder = new ViewHolderA(view);
            return viewHolder;
        }else{
            View view =  LayoutInflater.from(context).inflate(R.layout.blue_item,null);
            ViewHolderB viewHolder = new ViewHolderB(view);
            return viewHolder;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolderA){
            ((ViewHolderA) holder).text.setText(data.get(position).getNum());
        }else{
            ((ViewHolderB) holder).text.setText(data.get(position).getNum());
        }
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (data.get(position).isIsred()){
            return Item_Type.RED.ordinal();
        }else {
            return Item_Type.BLUE.ordinal();
        }

    }

    class ViewHolderA extends RecyclerView.ViewHolder {
        public TextView text;

        public ViewHolderA(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.tv_red);
        }
    }


    class ViewHolderB extends RecyclerView.ViewHolder {

        public TextView text;

        public ViewHolderB(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.tv_blue);
        }
    }
}
