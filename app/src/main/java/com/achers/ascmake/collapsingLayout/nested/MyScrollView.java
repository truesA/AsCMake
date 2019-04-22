package com.achers.ascmake.collapsingLayout.nested;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.NestedScrollingParent2;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created on 2019/4/10 18:09
 * <p>
 * author lhm
 * <p>
 * Description:
 * <p>
 * Remarks:
 */
public class MyScrollView extends LinearLayout implements NestedScrollingParent2 {

    private int mAxes;

    public MyScrollView(Context context) {
        this(context,null);
    }

    public MyScrollView(Context context,  @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyScrollView(Context context,  @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



    public boolean onStartNestedScroll(@NonNull View child, @NonNull View target, int axes, int type) {
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }


    @Override
    public void onNestedScrollAccepted(@NonNull View child, @NonNull View target, int axes, int type) {
        mAxes = axes;
    }

    @Override
    public void onStopNestedScroll(@NonNull View target, int type) {
        mAxes = SCROLL_AXIS_NONE;
    }



    @Override
    public void onNestedScroll(@NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {

    }

    @Override
    public void onNestedPreScroll(@NonNull View view, int i, int i1, @NonNull int[] ints, int i2) {

    }
}
