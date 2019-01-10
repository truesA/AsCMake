package com.achers.ascmake;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;


/**
 * Create on 2017/12/28 22:27
 * <p>
 * author lhm
 * <p>
 * Description:
 * <p>
 * Version: 1.2.3
 */
public class App extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context=this;

        ARouter.openLog();     // 打印日志
        ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)

        ARouter.init(this); // 尽可能早，推荐在Application中初始化

//        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {
//
//            @Override
//            public void onViewInitFinished(boolean arg0) {
//                // TODO Auto-generated method stub
//                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
//                Log.d("app", " onViewInitFinished is " + arg0);
//            }
//
//            @Override
//            public void onCoreInitFinished() {
//                // TODO Auto-generated method stub
//            }
//        };
//        //x5内核初始化接口
//        QbSdk.initX5Environment(getApplicationContext(),  cb);
    }

    /**
     * 获取应用上下文对象
     */
    public static Context getAppContext(){
        return context;
    }

}
