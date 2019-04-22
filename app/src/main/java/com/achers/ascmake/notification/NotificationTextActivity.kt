package com.achers.ascmake.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.NotificationCompat
import android.util.Log
import android.view.View
import com.achers.ascmake.R
import com.achers.ascmake.arout.AroutActivity
import com.achers.ascmake.recyclers.RecyclerMainActivity
import com.achers.ascmake.vlayouts.vdemo.presenter.MainView

class NotificationTextActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification_text)
    }

    fun sendChatMsg(view:View){
        val manager =getSystemService(Context.NOTIFICATION_SERVICE)as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            //关键代码  手动去创建渠道id 才行 不然无法弹出
            val channel = NotificationChannel("chat",
                    "臻茶", NotificationManager.IMPORTANCE_DEFAULT)
            manager.createNotificationChannel(channel)
            //关键代码  手动去创建渠道id 才行 不然无法弹出


            val notification =NotificationCompat.Builder(this,"chat")
                    .setContentTitle("收到一条聊天信息")
                    .setContentText("今天中午吃什么？")
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(R.drawable.icon)
                    .setLargeIcon(BitmapFactory.decodeResource(resources,R.drawable.icon))
                    .setAutoCancel(true)
                    .build()
            manager.notify(1,notification)
        }
    }

    fun sendSubscribeMsg(view:View) {
        val intent =Intent(this, RecyclerMainActivity::class.java)

        val pendingIntent = PendingIntent.getActivity(this, 1, intent, PendingIntent.FLAG_ONE_SHOT)

        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel("subscribe",
                    "臻茶", NotificationManager.IMPORTANCE_DEFAULT)

            manager.createNotificationChannel(channel)

            var notification = NotificationCompat.Builder(this, "subscribe")
                    .setContentTitle("收到一条订阅消息")
                    .setContentText("地铁沿线30万商铺抢购中！")
                    .setSmallIcon(R.drawable.icon)
//                    .setContentTitle("测试 1")
//                    .setContentText("hhhhh")
                    .setContentIntent(pendingIntent)
                    .setNumber(2)
                    .setDefaults(Notification.DEFAULT_ALL)

        val builds = notification.build()
        builds.flags = Notification.FLAG_AUTO_CANCEL

        manager.notify(2, builds)
    }


//        val mBuilder: NotificationCompat.Builder
//        val mNotificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val channelId = "ASIO_TEA"
//            val channelName = "臻茶"
//
//            val channel = NotificationChannel(channelId,
//                    channelName, NotificationManager.IMPORTANCE_DEFAULT)
//
//            mNotificationManager.createNotificationChannel(channel)
//            mBuilder = NotificationCompat.Builder(this, channelId)
//                    .setSmallIcon(R.drawable.icon)
//                    .setContentTitle("测试 1")
//                    .setContentText("hhhhh")
//                    .setDefaults(Notification.DEFAULT_ALL)
////                    .setContentIntent(pendingIntent)
//            //                            .setChannelId(channelId);
//
//        } else {
//            mBuilder = NotificationCompat.Builder(this, "ASIO_TEA")
//                    .setSmallIcon(R.drawable.icon)
//                    .setContentTitle("测试 2")
//                    .setContentText("hhhhh")
//                    .setDefaults(Notification.DEFAULT_ALL)
////                    .setContentIntent(pendingIntent)
//        }
//
//        val build = mBuilder.build()
//        build.flags = Notification.FLAG_AUTO_CANCEL
//
//        mNotificationManager.notify(2, build)
//        UtilsApplication.showNotification(this,R.drawable.icon,"ces","dasd", Intent())
    }


}
