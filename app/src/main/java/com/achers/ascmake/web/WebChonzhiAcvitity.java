package com.achers.ascmake.web;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.achers.ascmake.R;
import java.util.HashMap;
import java.util.Map;

/**
 * Create on 2018/3/8 14:42
 * <p>
 * author lhm
 * <p>
 * Description:
 * <p>
 * Version: 1.2.3
 */
public class WebChonzhiAcvitity extends AppCompatActivity {

    private Context context = this;
    private String url;
    private String initUrl;//不拼接UserID的URL
    private String title;

    private WebView mWebView;
    private boolean isFirstLoad = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webchongzhi);
        //初始化
        initView();

        mWebView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);
            }

            @Override
            public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
                super.onReceivedHttpError(view, request, errorResponse);
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();  // 接受所有网站的证书
                super.onReceivedSslError(view, handler, error);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url1) {
                Map headers = new HashMap();
                if (url1.contains("wx.tenpay.com")) {
                    //wx.tenpay.com 收银台点击微信时，shouldOverrideUrlLoading会调用两次，这里是第二次
                    headers.put("Referer", "https://payh5.bbnpay.com");
                } else {
                    //payh5.bbnpay
                    if(!isFirstLoad){
                        //跳转到收银台
                        headers.put("Referer", "https://shop.cp988.cn");
                        isFirstLoad = true;
                    }else{
                        //收银台点击微信时，shouldOverrideUrlLoading会调用两次，这里是第一次
                        headers.put("Referer", "https://payh5.bbnpay.com");
                    }
                }
                view.loadUrl(url1, headers);
                return true;
            }



        });
        String targetUrl="https://payh5.bbnpay.com/h5pay/way.php?data=%7B%22appid%22%3A%225109201803074191%22%2C%22transid%22%3A%220041911520563455564607996933%22%2C%22paytype%22%3A1%2C%22backurl%22%3A%22https%3A%2F%2Fwww.cp988.cn%22%7D&sign=4ac3aa89376eedd0bf695c66f3d6a1c1&signtype=MD5";

//        if (("4.4.3".equals(android.os.Build.VERSION.RELEASE))
//                || ("4.4.4".equals(android.os.Build.VERSION.RELEASE))) {
//            //兼容这两个版本设置referer无效的问题
//            mWebView.loadDataWithBaseURL("https://shop.cp988.cn",
//                    "<script>window.location.href=\"" + targetUrl + "\";</script>",
//                    "text/html", "utf-8", null);
//        } else {
            Map<String, String> extraHeaders = new HashMap<>();
            extraHeaders.put("Referer", "https://shop.cp988.cn");
            mWebView.loadUrl(targetUrl, extraHeaders);
      //  }

//        String Referer="https://shop.cp988.cn";
//        String url="https://payh5.bbnpay.com/h5pay/way.php?data=%7B%22appid%22%3A%225109201803074191%22%2C%22transid%22%3A%220041911520495276024819698456%22%2C%22paytype%22%3A1%2C%22backurl%22%3A%22https%3A%2F%2Faccount.cp988.cn%2Fapi%2Fv2%2Fnotify%2Furl%22%7D&sign=cb5a8e0f360300f69d73e5c79ec4cf23&signtype=MD5";
//        String htmldata="<script>\n"+"window.location.href=\""+url+"\";\n"+"</script>";
//        mWebView.loadDataWithBaseURL(Referer,htmldata,"text/html", "utf-8",null);
        // mWebView.loadUrl("https://payh5.bbnpay.com/h5pay/way.php?data=%7B%22appid%22%3A%225109201803074191%22%2C%22transid%22%3A%220041911520495276024819698456%22%2C%22paytype%22%3A1%2C%22backurl%22%3A%22https%3A%2F%2Faccount.cp988.cn%2Fapi%2Fv2%2Fnotify%2Furl%22%7D&sign=cb5a8e0f360300f69d73e5c79ec4cf23&signtype=MD5");
//
    }

    @Override
    protected void onResume() {
        super.onResume();
        mWebView.onResume();
    }

    private void initView() {
        // TODO Auto-generated method stub
        // customViewContainer = (FrameLayout)

        mWebView = (WebView) findViewById(R.id.web);
        WebSettings webSettings = mWebView.getSettings();
        mWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setAppCacheMaxSize(1024 * 1024 * 8);
        String appCachePath = context.getApplicationContext().getCacheDir()
                .getAbsolutePath();
        webSettings.setAppCachePath(appCachePath);
        webSettings.setAllowFileAccess(true);
        webSettings.setAppCacheEnabled(true);
    }



    @Override
    protected void onPause() {
        super.onPause();
        mWebView.onPause();

    }

    @Override
    protected void onDestroy() {
        try {
            if (mWebView != null) {
                if (null != mWebView.getParent()) {
                    ((ViewGroup) mWebView.getParent()).removeView(mWebView);
                }
                mWebView.removeAllViews();
                mWebView.destroy();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }
}
