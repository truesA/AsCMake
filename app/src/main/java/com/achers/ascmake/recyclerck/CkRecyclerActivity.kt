package com.achers.ascmake.recyclerck

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.achers.ascmake.R
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_ck_recycler.*


class CkRecyclerActivity : AppCompatActivity() {
    private val list = ArrayList<String>()
    private lateinit var adapter: MyRecyclerViewAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ck_recycler)

        for (i in 0 until 20) {
            list.add("条目" + i);
        }


        //给RecyclerView设置布局管理器
        recyclerView.setLayoutManager(LinearLayoutManager(getApplicationContext()));
        //初始化适配器
        adapter = MyRecyclerViewAdapter(getApplicationContext(), list);
        //给recyclerView添加适配器
        recyclerView.setAdapter(adapter);
        //设置分隔线
        recyclerView.addItemDecoration(DividerItemDecoration(this, 1));
        //设置增加或删除条目的动画
        recyclerView.setItemAnimator(DefaultItemAnimator());

        nochexbox.setOnClickListener(View.OnClickListener() {

            adapter.unSelectAll();
        });
        allchexbox.setOnClickListener(View.OnClickListener() {

            adapter.selectAll();
        });
        delete.setOnClickListener(View.OnClickListener() {

            adapter.deletingData();
        });
        alldelete.setOnClickListener(View.OnClickListener() {

            adapter.deleteAllData();
        });
    }

}
