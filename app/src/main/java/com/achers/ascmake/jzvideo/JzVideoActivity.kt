package com.achers.ascmake.jzvideo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import cn.jzvd.Jzvd
import com.achers.ascmake.R


/**
 * Created on 2019/7/21 19:47
 * <p>
 * author lhm
 * <p>
 * Description:
 * <p>
 * Remarks:
 */
class JzVideoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jz)

        val jzvdStd = findViewById<View>(R.id.jz_video) as MyJzvdStd
        jzvdStd.setUp("http://jzvd.nathen.cn/c6e3dc12a1154626b3476d9bf3bd7266/6b56c5f0dc31428083757a45764763b0-5287d2089db37e62345123a1be272f8b.mp4", "饺子闭眼睛")
//        jzvdStd.thumbImageView.setImage("http://p.qpic.cn/videoyun/0/2449_43b6f696980311e59ed467f22794e792_1/640")
    }


    override fun onPause() {
        super.onPause()
        Jzvd.releaseAllVideos()
    }
}