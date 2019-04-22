package com.achers.ascmake.seepic;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.achers.ascmake.seepic.interf.OnDragChangeListener;


/**
 * wrap ViewPager, process drag event.
 */
public class DragContainer extends FrameLayout {
    private static final String TAG = "DragContainer";
    private ViewDragHelper dragHelper;
    private ViewPager viewPager;
    private ArgbEvaluator argbEvaluator = new ArgbEvaluator();
    private int HideTopThreshold = 80;
    private int maxOffset;
    private OnDragChangeListener dragChangeListener;

    public DragContainer(@NonNull Context context) {
        this(context, null);
    }

    public DragContainer(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DragContainer(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        HideTopThreshold = dip2px(HideTopThreshold);
        dragHelper = ViewDragHelper.create(this, cb);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() != 1) {
            throw new IllegalArgumentException("Need only 1 child!");
        }
        viewPager = (ViewPager) getChildAt(0);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        maxOffset = getHeight() / 3;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touchX = ev.getX();
                touchY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float dx = ev.getX() - touchX;
                float dy = ev.getY() - touchY;
                // detect horizontal move, than throw to ViewPager see if it need first.
                // if ViewPager consumed, than end.
                if(Math.abs(dx) >= Math.abs(dy)){
                    viewPager.dispatchTouchEvent(ev);
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                touchX = 0;
                touchY = 0;
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    private float touchX, touchY;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (ev.getPointerCount() > 1) return super.onInterceptTouchEvent(ev);
        return dragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        dragHelper.processTouchEvent(ev);
        return true;
    }

    ViewDragHelper.Callback cb = new ViewDragHelper.Callback() {
        @Override
        public boolean tryCaptureView(@NonNull View view, int i) {
            return true;
        }

        @Override
        public void onViewCaptured(@NonNull View capturedChild, int activePointerId) {
            super.onViewCaptured(capturedChild, activePointerId);
        }

        @Override
        public int getViewVerticalDragRange(@NonNull View child) {
            return 1;
        }

        @Override
        public int getViewHorizontalDragRange(@NonNull View child) {
            return 0;
        }

        @Override
        public int clampViewPositionVertical(@NonNull View child, int top, int dy) {
            int t = child.getTop() + dy / 2;
            if (t >= 0) {
                return Math.min(t, maxOffset);
            } else {
                return -Math.min(-t, maxOffset);
            }
        }

        @Override
        public void onViewPositionChanged(@NonNull View changedView, int left, int top, int dx, int dy) {
            super.onViewPositionChanged(changedView, left, top, dx, dy);
            float fraction = Math.abs(top) * 1f / maxOffset;
            float pageScale = 1 - fraction * .15f;
            viewPager.setScaleX(pageScale);
            viewPager.setScaleY(pageScale);
            ((ViewGroup)getParent()).setBackgroundColor((Integer) argbEvaluator.evaluate(fraction * .8f, Color.BLACK, Color.TRANSPARENT));
            if(dragChangeListener!=null){
                dragChangeListener.onDragChange(dy, pageScale);
            }
        }

        @Override
        public void onViewReleased(@NonNull View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);
            if (Math.abs(releasedChild.getTop()) > HideTopThreshold) {
                if(dragChangeListener!=null)dragChangeListener.onRelease();
            } else {
                dragHelper.smoothSlideViewTo(viewPager, 0, 0);
                ViewCompat.postInvalidateOnAnimation(DragContainer.this);
            }
        }
    };

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (dragHelper.continueSettling(false)) {
            ViewCompat.postInvalidateOnAnimation(DragContainer.this);
        }
    }

    public int dip2px(float dpValue) {
        float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public void setOnDragChangeListener(OnDragChangeListener listener){
        this.dragChangeListener = listener;
    }
}
