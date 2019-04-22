package com.achers.ascmake.recyclers

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Button
import android.widget.Toast
import com.achers.ascmake.R
import com.achers.ascmake.arout.SlideBackActivity
import com.achers.ascmake.arout.slide.SlideBack
import com.achers.ascmake.arout.slide.callback.SlideBackCallBack



/**
 * Created on 2018/12/20 09:55
 * <p>
 * author lhm
 * <p>
 * Description:
 * <p>
 * Remarks:
 */
class RecyclerMainActivity : AppCompatActivity() {

    private lateinit var adapterEx : ExRecyclerAdapter
    private lateinit var adapterEx2 : ExRecyclerAdapter
    var datas: ArrayList<ItemBean>? = null
    var datas2: ArrayList<ItemBean>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recycler_add_layout)


        // 在需要滑动返回的Activity中注册，最好但非必须在onCreate中
        SlideBack.register(this, true) {
            finish()
        }

        datas = ArrayList()
        datas!!.add(ItemBean())
        datas2 = ArrayList()
        datas2!!.add(ItemBean())

        val recycler =findViewById<RecyclerView>(R.id.rc_view)
        recycler.layoutManager=LinearLayoutManager(this)
        adapterEx=ExRecyclerAdapter(this,datas,R.layout.item)
        recycler.adapter=adapterEx

        val recycler2 =findViewById<RecyclerView>(R.id.rc_view2)
        recycler2.layoutManager=LinearLayoutManager(this)
        adapterEx2=ExRecyclerAdapter(this,datas2,R.layout.item)
        recycler2.adapter=adapterEx2


        val add=findViewById<Button>(R.id.add_bt)
        val add2=findViewById<Button>(R.id.add_bt2)
        add.setOnClickListener {
            adapterEx.addData(ItemBean())
//            datas!!.add(ItemBean())
//            adapterEx.notifyDataSetChanged()
        }
        add2.setOnClickListener {
            adapterEx2.addData(ItemBean())
//            datas!!.add(ItemBean())
//            adapterEx.notifyDataSetChanged()
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        SlideBack.unregister(this)
    }

}