package com.achers.ascmake

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager

/**
 * Created on 2019/2/2 16:34
 * <p>
 * author lhm
 * <p>
 * Description:
 * <p>
 * Remarks:
 */
class AppTracker : Application.ActivityLifecycleCallbacks {
    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
//        UtilsLog.log("ACTIVITY::onActivityCreated", activity?.localClassName)
        handleDi(activity)

    }

    private fun handleDi(activity: Activity?) {
//        if (activity is Injectable) {
//            AndroidInjection.inject(activity)
//        }

        (activity as? FragmentActivity)
                ?.supportFragmentManager
                ?.registerFragmentLifecycleCallbacks(
                        FragmentLifecyclerTracker(), true
                )
    }

    class FragmentLifecyclerTracker :
            android.support.v4.app.FragmentManager.FragmentLifecycleCallbacks() {

//        override fun onFragmentPreCreated(
//            fm: android.support.v4.app.FragmentManager?,
//            f: Fragment?,
//            savedInstanceState: Bundle?
//        ) {
//            super.onFragmentPreCreated(fm, f, savedInstanceState)
//        }

        override fun onFragmentAttached(fm: android.support.v4.app.FragmentManager, f: Fragment, context: Context) {
        }

//        override fun onFragmentAttached(
//                fm: android.support.v4.app.FragmentManager?,
//                f: Fragment?,
//                context: Context?
//        ) {
////            if (f is Injectable) {
////                UtilsLog.log(f.javaClass.name)
////                AndroidSupportInjection.inject(f)
////            }
//        }

//        override fun onFragmentCreated(fm: android.support.v4.app.FragmentManager?, f: Fragment?, savedInstanceState: Bundle?) {
//            if (f is Injectable) {
//                UtilsLog.log(f.javaClass.name)
//                AndroidSupportInjection.inject(f)
//            }
//        }
    }

    override fun onActivityStarted(activity: Activity?) {
     //   UtilsLog.log("ACTIVITY::onActivityStarted", activity?.localClassName)
    }

    override fun onActivityResumed(activity: Activity?) {
       // UtilsLog.log("ACTIVITY::onActivityResumed", activity?.localClassName)
    }

    override fun onActivityPaused(activity: Activity?) {
      //  UtilsLog.log("ACTIVITY::onActivityPaused", activity?.localClassName)
    }

    override fun onActivityStopped(activity: Activity?) {
       // UtilsLog.log("ACTIVITY::onActivityStopped", activity?.localClassName)
    }

    override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
        //UtilsLog.log("ACTIVITY::onActivitySaveInstanceState", activity?.localClassName)
    }

    override fun onActivityDestroyed(activity: Activity?) {
//        UtilsLog.log("ACTIVITY::onActivityDestroyed", activity?.localClassName)
//        if(activity?.localClassName=="ui.main.CameraMainActivity"){
//            Hawk.put(SPKey.isShowSplash,true)
//        }
//        ImmersionBar.with(activity!!).destroy()
    }
}
