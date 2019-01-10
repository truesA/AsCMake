package com.achers.ascmake.proxys.web;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.WebView;

/**
 * author：lhm on 2018/7/5 11:18
 * <p>
 * email：3186834196@qq.com
 */
public class PWeb extends WebView implements Iweb {
    public PWeb(Context context) {
        super(context);
    }

    public PWeb(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PWeb(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void JavaFuckJSInterface() {
        Log.e("proxyweb","===========JavaFuckJSInterface"+ "");
    }

    @Override
    public void shouldOverrideUrlLoading() {
        Log.e("proxyweb","===========shouldOverrideUrlLoading"+ "");
    }

//    @Override
//    public void loadUrl(String url) {
//        super.loadUrl(url);
//        Log.e("proxyweb","===========loadUrl"+ "");
//    }
}
