package com.achers.ascmake.components.componentWeb;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.achers.ascmake.components.beatweb.Param;

import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.util.Iterator;
import java.util.Set;

/**
 * Created on 2018/7/10 17:21
 * <p>
 * author lhm
 * <p>
 * Description:
 * <p>
 * Remarks: 抽象的装饰者 持有一个被装饰者
 */
public class DecoratorWeb extends WebBeas {

    public DecoratorWeb(Context context, WebView webView, Param param) {
        super(context, webView, param);
    }


    @Override
    public void loadUrl(String url) {
        loadUrlBefore();
        webView.loadUrl(url);
        loadUrlAfter();

    }



    @Override
    public void setWebViewClient(){
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);//支持javascript
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);//设置允许js弹出alert对话框
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);  //设置 缓存模式
        webSettings.setUseWideViewPort(true);//扩大比例的缩放
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);//自适应屏幕
        webSettings.setLoadWithOverviewMode(true);

        webView.requestFocus();//触摸焦点起作用
        webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);//取消滚动条

        //设置了Alert才会弹出，重新onJsAlert（）方法return true可以自定义处理信息
        webView.setWebChromeClient(new WebClient());
//        webView.addJavascriptInterface(new JavaFuckJSInterface(), "android");
        webView.setWebViewClient(new TheWebViewClient());

//        Toast.makeText(context, "context", Toast.LENGTH_LONG).show();
    }

    public void payAction() {
        super.payAction();
    }

    public void onKeyDownWeb(int keyCode, KeyEvent event) {

    }

    class WebClient extends WebChromeClient {

        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
            return true;
        }

        @Override
        public void onProgressChanged(WebView view, int i) {
            super.onProgressChanged(view, i);
        }

    }


    /**
     * 和H5的交互
     */
    class TheWebViewClient extends android.webkit.WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
//            if (url.startsWith("rzsj")) {
//                navigationManager.handleH5Interact(url);
//                return true;
//            } else if (url.startsWith("weixin") || url.startsWith("alipays")) {
//                open(url);
//                return true;
//            }
            Toast.makeText(context, "url", Toast.LENGTH_LONG).show();
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
//            showLoadingTransparent();
//            navigationManager.handleH5Interact(url);
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            //  disableLoadingTransparent();
            super.onPageFinished(view, url);
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed();
        }
    }

    public class MyWebViewClient extends WebViewClient {

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            //      Logger.d("shouldOverrideUrlLoading=" + url);
            doSchemeJump(url);
            return true;
        }

        @Override
        public void onReceivedError(WebView view, int errorCode,
                                    String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
        }

    }

    /**
     * 根据scheme 协议作出响应跳转是跳系统浏览器还是应用内页面还是用webView 打开
     */
    public void doSchemeJump(String linkUrl) {
        try {
//            Logger.d("url=" + linkUrl);
            if (!TextUtils.isEmpty(linkUrl)) {
                Uri uri = Uri.parse(linkUrl);
                String scheme = uri.getScheme();
                String host = uri.getHost();

                if (scheme.equals("http") || scheme.equals("https")) {
                    loadUrl(linkUrl, uri);
                } else {
                    // 调用系统浏览器
                    openBrowser(linkUrl);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadUrl(String linkUrl, Uri uri) {
        Bundle bundle = parseExtras(uri);
        if (bundle != null) {
//            Logger.d(tIntent.toString());
            if (bundle.containsKey("scheme")) {
                String scheme = bundle.getString("scheme");
                if (scheme != null && scheme.startsWith("alipays")) {
                    String schemeUrl = URLDecoder.decode(scheme);
                    try {
                        open(schemeUrl);
                    } catch (Exception e) {
                        e.printStackTrace();
                        openBrowser(linkUrl);
                        ((Activity)context).finish();
                    }
                    return;
                }
            }
        }
        webView.loadUrl(linkUrl);
    }

    public static Bundle parseExtras(Uri uri) {
//        Logger.d("parseExtras:" + uri.toString());
        Bundle extras = null;
        Set<String> queryParameterNames = uri.getQueryParameterNames();
        Iterator<String> iterator = queryParameterNames.iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            String value = uri.getQueryParameter(key);
            if (extras == null) {
                extras = new Bundle();
            }
            extras.putString(key, value);
        }

        return extras;
    }


    private void openBrowser(String url) {
        try {
            Uri uri = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void open(String url) throws URISyntaxException {
        Intent intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME);
        intent.addCategory("android.tIntent.category.BROWSABLE");
        intent.setComponent(null);
        context.startActivity(intent);
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
