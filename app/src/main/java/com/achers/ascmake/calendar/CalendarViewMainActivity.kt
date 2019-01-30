package com.achers.ascmake.calendar

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomSheetDialog
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import cn.addapp.pickers.common.LineConfig
import cn.addapp.pickers.util.ConvertUtils
import cn.addapp.pickers.widget.WheelListView
import cn.addapp.pickers.widget.WheelView
import com.achers.ascmake.R
import com.haibin.calendarview.Calendar
import com.haibin.calendarview.CalendarView
import kotlinx.android.synthetic.main.activity_calendar_view_main.*
import kotlinx.android.synthetic.main.select_time_bottom_dialog.*
import cn.addapp.pickers.adapter.ArrayWheelAdapter
import cn.addapp.pickers.listeners.OnItemPickListener


class CalendarViewMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar_view_main)

        calendarView.setMonthView(SimpleMonthView::class.java)
//        calendarView.setMonthView(MeiZuMonthView::class.java)
//        calendarView.setMonthView(ProgressMonthView::class.java)
//        calendarView.setMonthView(SingleMonthView::class.java)
//        calendarView.setMonthView(IndexMonthView::class.java)
        calendarView.setOnMonthChangeListener(object : CalendarView.OnMonthChangeListener {
            @SuppressLint("SetTextI18n")
            override fun onMonthChange(year: Int, month: Int) {
                Log.e("`------", "year---$year----month$month")
                data_tv.text = "${year}年-${month}月"
            }

        })

        calendarView.setOnCalendarSelectListener(object : CalendarView.OnCalendarSelectListener {
            override fun onCalendarSelect(calendar: Calendar?, isClick: Boolean) {
                Log.e("------Select", "Boolean${isClick}-----${calendar!!.year}---${calendar.month}----${calendar.day}")
            }

            override fun onCalendarOutOfRange(calendar: Calendar?) {
                Log.e("------OfRange", "${calendar!!.year}---${calendar.month}----${calendar.day}")
            }

        })
    }

    fun onTime(view: View) {
        showBottomDialog()
    }


    private fun showBottomDialog() {
        val bottomDialog =  Dialog(this, R.style.BottomDialog)
        val contentView = LayoutInflater.from(this).inflate(R.layout.select_time_bottom_dialog, null);
        bottomDialog.setContentView(contentView);
        val layoutParams = contentView.layoutParams as ViewGroup.LayoutParams;
        layoutParams.width = resources.displayMetrics.widthPixels
        contentView.layoutParams = layoutParams;
        bottomDialog.window.setGravity(Gravity.BOTTOM);
        bottomDialog.window.setWindowAnimations(R.style.BottomDialog_Animation);
        bottomDialog.show()

        val submit = contentView.findViewById<TextView>(R.id.tv_submit)
        val cancel = contentView.findViewById<TextView>(R.id.tv_cancel)
        val hour = contentView.findViewById<WheelView>(R.id.wheel_view_hour)
        val minute = contentView.findViewById<WheelView>(R.id.wheel_view_minute)


        var lineConfig = LineConfig()
        lineConfig.setAlpha(1);
//        lineConfig.thick = ConvertUtils.toPx(this, 3.0f).toFloat()

        val hourString = ArrayList<String>()
        for (i in 0 until 24) {
            if (i<10){
                hourString.add("0${i}点")
            }else{
                hourString.add("${i}点")
            }
        }
        val hourAdapter =ArrayWheelAdapter(hourString)
        hour.setAdapter(hourAdapter)
        hour.setCanLoop(true)
        hour.setUnSelectedTextColor(resources.getColor(R.color.black))
        hour.setSelectedTextColor(resources.getColor(R.color.blue))

        hour.setLineConfig(lineConfig)
        hour.setOnItemPickListener(object :OnItemPickListener<String>{
            override fun onItemPicked(index: Int, item: String?) {
                Log.e("------Select",item)
            }

        })

        val minuteString = ArrayList<String>()
        for (i in 0 until 60) {
            if (i<10){
                minuteString.add("0${i}分")
            }else{
                minuteString.add("${i}分")
            }
        }
        val minuteAdapter =ArrayWheelAdapter(minuteString)
        minute.setAdapter(minuteAdapter)
        minute.setCanLoop(true)
        minute.setUnSelectedTextColor(resources.getColor(R.color.black))
        minute.setSelectedTextColor(resources.getColor(R.color.blue))
        minute.setLineConfig(lineConfig)
        minute.setOnItemPickListener(object :OnItemPickListener<String>{
            override fun onItemPicked(index: Int, item: String?) {
                Log.e("------Select",item)
            }

        })

        cancel.setOnClickListener {
            bottomDialog.dismiss()
        }
        submit.setOnClickListener {
            bottomDialog.dismiss()
        }
    }
}
