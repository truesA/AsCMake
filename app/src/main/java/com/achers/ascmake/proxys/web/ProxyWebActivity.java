package com.achers.ascmake.proxys.web;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.achers.ascmake.R;

public class ProxyWebActivity extends AppCompatActivity {

    private PWeb webview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proxy_web);

        webview=findViewById(R.id.webview);

        String url="https://blog.csdn.net/self_study/article/details/55050627";

//        webview.loadUrl(url);

        Iweb iweb= new WebViewProxy(webview).getIwebProxy();

        iweb.loadUrl(url);
//
//        iweb.JavaFuckJSInterface();
//
//        iweb.shouldOverrideUrlLoading();

    }
}
