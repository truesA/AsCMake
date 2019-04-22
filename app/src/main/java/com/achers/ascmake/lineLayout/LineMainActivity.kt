package com.achers.ascmake.lineLayout

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.achers.ascmake.R
import kotlinx.android.synthetic.main.activity_line_main.*

class LineMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_line_main)
        line_layout.setAdapter(object :LineAdapter(){

            override fun getCount(): Int {
                return 10
            }

            override fun getView(position: Int, parent: ViewGroup?): View {
                val imageView = ImageView(parent!!.getContext())
                imageView.setImageResource(R.mipmap.ic_launcher_round)
                if (position === 0) {
                    imageView.setImageResource(R.mipmap.ic_launcher)
                }
                imageView.setOnClickListener(View.OnClickListener { Toast.makeText(this@LineMainActivity, "position--" + position, Toast.LENGTH_SHORT).show() })
                return imageView
            }


        })
    }
}
