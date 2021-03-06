package com.achers.ascmake.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Create on 2018/3/14 22:02
 * <p>
 * author lhm
 * <p>
 * Description:
 * <p>
 * Version: 1.2.3
 */
public class OnclickLinerLayout extends LinearLayout {


    private static final int FLING_MIN_DISTANCE = 20;// 移动最小距离
    private static final int FLING_MIN_VELOCITY = 200;// 移动最大速度


    public OnclickLinerLayout(Context context) {
        this(context, null);
    }

    public OnclickLinerLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public OnclickLinerLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }

    public boolean onTouchEvent(MotionEvent event) {

        //获得listview的个数
        int count = getChildCount();
        Log.e("count", count + "");

        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            try {
                child.dispatchTouchEvent(event);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }

//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        switch (ev.getAction()) {
//            case MotionEvent.ACTION_UP:
////                //break;
//                return true;
//        }
//
//        return super.dispatchTouchEvent(ev);
//    }


    float mLastX;
    float mLastY;

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        int dx = 0;
        int dy = 0;
        float xx = event.getX();
        float yy = event.getY();
        Log.e("dispatchTouchEvent", "x--" + xx + "y--" + yy);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                mLastX = xx;
                mLastY = yy;
                // onTouchEvent(event);
                break;

            case MotionEvent.ACTION_MOVE:



                break;
            case MotionEvent.ACTION_UP:
                break;

        }

        return super.dispatchTouchEvent(event);
    }


    private static final String TAG = "MyGestureListener";


    public static interface OnScrollChangedCallback {
        public void webonScroll(int dx, int dy);
    }
    private OnScrollChangedCallback mChangedCallback;
    public OnScrollChangedCallback getOnScrollChangedCallback() {
        return mChangedCallback;
    }

    public void setOnScrollChangedCallback(
            final OnScrollChangedCallback onScrollChangedCallback) {
        mChangedCallback = onScrollChangedCallback;
    }

//    @Override
//    public boolean onDown(MotionEvent e) {
//        // TODO Auto-generated method stub
//        return true;
//    }
//
//    @Override
//    public void onShowPress(MotionEvent e) {
//        // TODO Auto-generated method stub
//
//    }
//
//    @Override
//    public boolean onSingleTapUp(MotionEvent e) {
//        // TODO Auto-generated method stub
//        return true;
//    }
//
//    @Override
//    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
//                            float distanceY) {
//        // TODO Auto-generated method stub
//        return true;
//    }
//
//    @Override
//    public void onLongPress(MotionEvent e) {
//        // TODO Auto-generated method stub
//
//    }
//
//    @Override
//    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
//                           float velocityY) {
//
//        Log.e(TAG, "onFling");
//        // TODO Auto-generated method stub
//        // e1：第1个ACTION_DOWN MotionEvent
//        // e2：最后一个ACTION_MOVE MotionEvent
//        // velocityX：X轴上的移动速度（像素/秒）
//        // velocityY：Y轴上的移动速度（像素/秒）
//
//        // X轴的坐标位移大于FLING_MIN_DISTANCE，且移动速度大于FLING_MIN_VELOCITY个像素/秒
//        //向
//        if (e1.getY() - e2.getY() > FLING_MIN_DISTANCE) {
////                     && Math.abs(velocityX) > FLING_MIN_VELOCITY) {
//            // collapse();
//        }
//        //向上
//        if (e2.getY() - e1.getY() > FLING_MIN_DISTANCE
//                && Math.abs(velocityX) > FLING_MIN_VELOCITY) {
//
//        }
//        return true;
//    }

}