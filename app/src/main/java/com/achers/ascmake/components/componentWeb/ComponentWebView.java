package com.achers.ascmake.components.componentWeb;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.WebView;

/**
 * Created on 2018/7/10 17:21
 * <p>
 * author lhm
 * <p>
 * Description:
 * <p>
 * Remarks:
 */
public class ComponentWebView extends WebView {

    public ComponentWebView(Context context) {
        this(context,null);
    }

    public ComponentWebView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ComponentWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

//    public void ComponentWebView(WebView webView) {
//        super.DecoratorWeb(webView);
//    }

    @Override
    public void loadUrl(String url) {
        loadUrlBefore();
        super.loadUrl(url);
        loadUrlAfter();
    }




    /**
     * 加载之前
     */
    private void loadUrlBefore(){
        //加头
        Log.e("ConcreteDecoratorA","加头");
    }

    /**
     * 加载之后
     */
    private void loadUrlAfter(){
        //防止泄露和发送反差消息
        Log.e("ConcreteDecoratorA","防止泄露和发送反差消息");
    }

}
