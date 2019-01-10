package com.achers.ascmake.components.componentWeb;

import android.content.Context;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebView;

import com.achers.ascmake.components.beatweb.Param;
import com.achers.ascmake.proxys.web.Iweb;

/**
 * Created on 2018/7/12 14:14
 * <p>
 * author lhm
 * <p>
 * Description:
 * <p>
 * Remarks:
 */
public class WebBeas  implements IWeb{

    protected WebView webView;
    protected Param param;
    protected Context context;


    public WebBeas(Context context, WebView webView, Param param){
        this.webView=webView;
        this.param=param;
        this.context=context;
    }


    @Override
    public void loadUrl(String url) {
        Log.e("loadUrlWebBeas","WebBeas");
    }

    @Override
    public void setWebViewClient() {

    }

    @Override
    public void payAction() {

    }

    @Override
    public void onKeyDownWeb(int keyCode, KeyEvent event) {

    }
}
