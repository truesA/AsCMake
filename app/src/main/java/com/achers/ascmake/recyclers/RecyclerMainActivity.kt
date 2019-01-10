package com.achers.ascmake.recyclers

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Button
import com.achers.ascmake.R

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



}