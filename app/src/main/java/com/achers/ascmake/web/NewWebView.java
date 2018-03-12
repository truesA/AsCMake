package com.achers.ascmake.web;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.WebView;

/**
 * Create on 2018/1/31 10:19
 * <p>
 * author lhm
 * <p>
 * Description:
 * <p>
 * Version: 1.2.3
 */
public class NewWebView extends WebView {

    private OnScrollChangedCallback mOnScrollChangedCallback;

    public NewWebView(Context context) {
        super(context);
    }

    public NewWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NewWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);

        float contentHeight = getContentHeight() * getScale();

        Log.e("onScrollChanged","dx---"+getScrollX()+"dy----"+contentHeight);
        if (mOnScrollChangedCallback!=null){
            mOnScrollChangedCallback.webonScroll(l-oldl,t-oldt);
        }
    }

    public OnScrollChangedCallback getOnScrollChangedCallback() {
        return mOnScrollChangedCallback;
    }

    public void setOnScrollChangedCallback(
            final OnScrollChangedCallback onScrollChangedCallback) {
        mOnScrollChangedCallback = onScrollChangedCallback;
    }

    public static interface OnScrollChangedCallback{
        public void webonScroll(int dx, int dy);
    }

}
