package com.achers.ascmake.web;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.achers.ascmake.CustomRecyclerView;
import com.achers.ascmake.CustomSGLayoutManager;
import com.achers.ascmake.R;
import com.achers.ascmake.view.TouchLinearLayout;

import java.util.ArrayList;

public class WebsActivity extends AppCompatActivity implements View.OnTouchListener {

    private static final String TAG = "WebsActivity";
    // private NewWebView mWebView;
    private WebView mWebView;
    //  private HorizontalScrollView horizontalScrollView;

    private GestureDetector mGestureDetector;
    private TouchLinearLayout linearLayout ,linearLayout2;

    private CustomRecyclerView recyclerView;



    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webs);



        linearLayout=findViewById(R.id.ll);
        linearLayout2=findViewById(R.id.ll2);



        mWebView = findViewById(R.id.web);

      //  mWebView.setOnTouchListener(this);


        mWebView.loadUrl("https://www.cp988.cn/my-project/run-chart/double/double.html");


        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);//支持javascript
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);//设置允许js弹出alert对话框
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);  //设置 缓存模式
        webSettings.setUseWideViewPort(true);//扩大比例的缩放
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);//自适应屏幕
        webSettings.setLoadWithOverviewMode(true);

        mWebView.requestFocus();//触摸焦点起作用
        mWebView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);//取消滚动条

        //设置了Alert才会弹出，重新onJsAlert（）方法return true可以自定义处理信息
        mWebView.setWebChromeClient(new WebsActivity.WebClient());

//        mWebView.setOnScrollChangedCallback(new NewWebView.OnScrollChangedCallback() {
//            @Override
//            public void webonScroll(int dx, int dy) {
//                Log.e("dxdy","dx---"+dx+"dy----"+dy);
//            }
//        });

        recyclerView = findViewById(R.id.rc);
        CustomSGLayoutManager ms = new CustomSGLayoutManager(this);

        ms.setOrientation(CustomSGLayoutManager.HORIZONTAL);// 设置 recyclerview 布局方式为横向布局
       ms.setSpeedRatio(0.80);
        recyclerView.setflingScale(0.1);
        //ms.setOrientation(0.5);
        recyclerView.setLayoutManager(ms);
        ArrayList<String> strings =new ArrayList<>();
        for (int i=1;i<49;i++){
            strings.add(i+"");
        }
        RcAdapter adapter =new RcAdapter(this,strings);
        recyclerView.setAdapter(adapter);

        linearLayout.setOnScrollChangedCallback(new TouchLinearLayout.OnScrollChangedCallback() {
            @Override
            public void webonScroll(int dx, int dy) {

                recyclerView.scrollBy(dx,dy);

            }
        });

       // mGestureDetector = new GestureDetector(this, new MyOnGestureListeners());

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        mGestureDetector.onTouchEvent(event);
        // 一定要返回true，不然获取不到完整的事件
        return false;
    }


    class MyOnGestureListeners extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            Log.i(getClass().getName(), "onSingleTapUp-----"  );
            return false;
        }

        @Override
        public boolean onDown(MotionEvent e) {
            Log.e(TAG, "onDown");
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            Log.e(TAG, "onScroll");
            return true;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            Log.i(getClass().getName(), "onLongPress-----" );
        }
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
//            if (e1.getX() - e2.getX() > FLING_MIN_DISTANCE
//                    && Math.abs(velocityX) > FLING_MIN_VELOCITY) {
//                // Fling left
//                Toast.makeText(WebsActivity.this, "Fling Left", Toast.LENGTH_SHORT).show();
//            } else if (e2.getX() - e1.getX() > FLING_MIN_DISTANCE
//                    && Math.abs(velocityX) > FLING_MIN_VELOCITY) {
//                // Fling right
//                Toast.makeText(WebsActivity.this, "Fling Right", Toast.LENGTH_SHORT).show();
//            }
          // super.onFling(e1,e2,200f,200f);
            return true;
        }
    }
    private static final int FLING_MIN_DISTANCE = 2000;//mListView  滑动最小距离
    private static final int FLING_MIN_VELOCITY = 1;//mListView 滑动最大速度



    class WebClient extends WebChromeClient {

        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            return true;
        }

        @Override
        public void onProgressChanged(WebView view, int i) {
            super.onProgressChanged(view, i);
        }

    }
}

