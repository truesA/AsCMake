package com.achers.ascmake.web;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.achers.ascmake.CustomRecyclerView;
import com.achers.ascmake.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WebAsActivity extends AppCompatActivity {
    private NewWebView newWebView;
    private CustomRecyclerView customRecyclerView,customRecyclerView2;





    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_as);
        
        newWebView=findViewById(R.id.webas);

        customRecyclerView=findViewById(R.id.rca);
        LinearLayoutManager ms = new LinearLayoutManager(this);

        ms.setOrientation(LinearLayoutManager.HORIZONTAL);// 设置 recyclerview 布局方式为横向布局
      //  ms.setSpeedRatio(0.80);
        customRecyclerView.setflingScale(0.1);
        //ms.setOrientation(0.5);
        customRecyclerView.setLayoutManager(ms);
        ArrayList<String> strings =new ArrayList<>();
        for (int i=1;i<149;i++){
            strings.add(i+"");
        }
        RcAdapter adapter =new RcAdapter(this,strings);
        customRecyclerView.setAdapter(adapter);



        customRecyclerView2=findViewById(R.id.rcaa);
        LinearLayoutManager mss = new LinearLayoutManager(this);
        mss.setOrientation(LinearLayoutManager.HORIZONTAL);// 设置 recyclerview 布局方式为横向布局
        customRecyclerView2.setflingScale(0.1);
        //ms.setOrientation(0.5);
        customRecyclerView2.setLayoutManager(mss);

        RcAdapter adapter2 =new RcAdapter(this,strings);
        customRecyclerView2.setAdapter(adapter2);


//        Map<String,String> extraHeaders = new HashMap<String, String>();
//        extraHeaders.put("referer", "https://shop.cp988.cn");
//        newWebView.loadUrl("https://payh5.bbnpay.com/h5pay/way.php?data=%7B%22appid%22%3A%225109201803074191%22%2C%22transid%22%3A%220041911520472381029425725400%22%2C%22paytype%22%3A1%2C%22backurl%22%3A%22https%3A%2F%2Faccount.cp988.cn%2Fapi%2Fv2%2Fnotify%2Furl%22%7D&sign=a6a6057a9b132ed811f67bfd1101d04b&signtype=MD5",extraHeaders);
        //load在线
        String url="<form name=\"punchout_form\" method=\"post\" action=\"https://openapi.alipay.com/gateway.do?charset=utf-8&method=alipay.trade.wap.pay&sign=X0O1CZ9Ir9DZzItd7opheRFrO9De7YhA9HC4gOT4GvMCf6STEFUbRZ1vXYvJkeSufseind6tDa%2B7aznZadrtN%2BJiMusrnhGfwCobSSmbV1T%2FTJWlCLDNg6ZJ8%2FpER%2Fh310SwrwnETheB4i1NTR7YRbPBLSY3iBRwgrBfY9eMDQeZBSlNbZMfCraaG1tG0IVR8OTgpjb6MJEoyx1ZNiv42pWsdE%2FoAC6KWcs8svhPYg%2Fl8UZCj5llJLTYiBPvcpk3O9KsSUGk74nTtNK3yy9IcLGtTp1cJuHSf9bOtlx6duP%2F3QQegTkcRY4LKBipbjcImcrGeYmFrhVJcXF12ofkBA%3D%3D&return_url=http%3A%2F%2Fpay.tomato-pay.com%2Fpay%2Fback%2Faliszryypay&notify_url=http%3A%2F%2Fpay.tomato-pay.com%2Fpay%2Fnotify%2Faliszryypay&version=1.0&app_id=2018031402370023&sign_type=RSA2&timestamp=2018-04-16+13%3A50%3A38&alipay_sdk=alipay-sdk-java-dynamicVersionNo&format=json\">\n" +
                "<input type=\"hidden\" name=\"biz_content\" value=\"{&quot;out_trade_no&quot;:&quot;152385783854886384445&quot;,&quot;total_amount&quot;:&quot;0.1&quot;,&quot;subject&quot;:&quot;lottery&quot;,&quot;body&quot;:&quot;account&quot;,&quot;product_code&quot;:&quot;QUICK_WAP_WAY&quot;}\">\n" +
                "<input type=\"submit\" value=\"立即支付\" style=\"display:none\" >\n" +
                "</form>\n" +
                "<script>document.forms[0].submit();</script>";
      // String str= getHtmlData(url);

//        newWebView.loadUrl("http://blog.csdn.net/zheng_jiao/article/details/51433462");
        //newWebView.loadDataWithBaseURL(null,url, "text/html","charset=UTF-8",null);

        String urls="https://www.cp988.cn/pay/pay.html?t=1527837828301&param=dXNlcklkPTIwMTcwOTIxMTAwOTE4ODk3MjEwMDAwMDcmdG9rZW49ODNkYjU0Zjk2ZDNkYTkwYmFhYWIzMjRlZDgwZjMyZjMzZWJiNGYxNSZjaGFubmVsSWQ9MjAwMDAwNuaIjWpncumrjsil4pue76CAJg==&schemaId=201806011523470139755643&schemaType=1&openSystemBrowser=1";
        newWebView.loadUrl(urls);
        //newWebView.scrollBy(100,10);

        WebSettings webSettings = newWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);//支持javascript
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);//设置允许js弹出alert对话框
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);  //设置 缓存模式
        webSettings.setUseWideViewPort(true);//扩大比例的缩放
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);//自适应屏幕
        webSettings.setLoadWithOverviewMode(true);
        newWebView.addJavascriptInterface(new JavaFuckJSInterface(), "AndroidJavaInterface");

        //newWebView.requestFocus();//触摸焦点起作用
        //newWebView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);//取消滚动条

//        //设置了Alert才会弹出，重新onJsAlert（）方法return true可以自定义处理信息
//        newWebView.setWebChromeClient(new WebClient());
//
//        newWebView.setOnScrollChangedCallback(new NewWebView.OnScrollChangedCallback() {
//            @Override
//            public void webonScroll(int dx, int dy) {
//                Log.e("RecyclerViewwebdy","==="+dy);
//               customRecyclerView.scrollBy(dy,dx);
//                customRecyclerView2.scrollBy(dy,dx);
//            }
//        });


        customRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
//                if (ms != null) {
//                    //获取RecyclerView当前顶部显示的第一个条目对应的索引
//                    int position = ms.findFirstVisibleItemPosition();
//                    //根据索引来获取对应的itemView
//                    View firstVisiableChildView = ms.findViewByPosition(position);
//                    //获取当前显示条目的高度
//                    int itemHeight = firstVisiableChildView.getHeight();
//                    //获取当前Recyclerview 偏移量
//                    int flag = (position) * itemHeight - firstVisiableChildView.getTop();
//
//                Message msg = new Message();
//                msg.what=1;
//                msg.arg1=dx;
//                msg.arg2=dy;
//                uiHandler.sendMessage(msg);

                //    Log.e()
                  //  Log.e("RecyclerView","scroll " + dx + " " + dy + " flag " + flag);


               // }
            }



        });


    }
    class JavaFuckJSInterface {
        //通过这个@JavascriptInterface转化成绑定的“App”对象下的同名函数，js代码可以直接调用
        @JavascriptInterface
        public void interactWithApp() {
//            UtilsLog.log(data);
            Log.e("Js", "data");
//            System.out.print("data");
        }
    }


    private String getHtmlData(String bodyHTML) {
        String head = "<head>" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, user-scalable=no\"> " +
                "<style>img{max-width: 100%; width:auto; height:auto;}</style>" +
                "</head>";
        return "<html>" + head + "<body>" + bodyHTML + "</body></html>";
    }

    @SuppressLint("HandlerLeak")
    private Handler uiHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    System.out.println("handleMessage thread id " + Thread.currentThread().getId());
                    System.out.println("msg.arg1:" + msg.arg1);
                    System.out.println("msg.arg2:" + msg.arg2);
                    newWebView.scrollBy(msg.arg1,msg.arg2);
                    break;
            }
        }
    };

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
