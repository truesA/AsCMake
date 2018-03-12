package com.achers.ascmake;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

public class RecyviewTxActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<RcTx> redList;
    private ArrayList<RcTx> buleList;
    private ArrayList<RcTx> allList;
    private RcTxAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyview_tx);
        recyclerView=findViewById(R.id.rvtx);
        LinearLayoutManager ms = new LinearLayoutManager(this);

        ms.setOrientation(LinearLayoutManager.HORIZONTAL);// 设置 recyclerview 布局方式为横向布局
        initData();
        recyclerView.setLayoutManager(ms);
        adapter =new RcTxAdapter(this,allList);
        recyclerView.setAdapter(adapter);
    }


    private void initData() {
//        redList= new ArrayList<>();
//        for (int i=0;i<33;i++){
//            redList.add(i,new RcTx(i+"",true));
//        }
//        Log.e("red",redList.size()+"");
//
//        buleList =new ArrayList<>();
//        for (int j=0;j<16;j++){
//            buleList.add(j,new RcTx(j+"",false));
//        }
//        Log.e("bule",buleList.size()+"");
//
//        int size =redList.size()+buleList.size();

        allList =new ArrayList<>();
        for (int i=1;i<50;i++){
            if (i<34){
                allList.add(new RcTx(i+"",true));
            }else{
                allList.add(new RcTx(i+"",false));
            }
        }
        Log.e("allList",allList.size()+"");

    }
}
