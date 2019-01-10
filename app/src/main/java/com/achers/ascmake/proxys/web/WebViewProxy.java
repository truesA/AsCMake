package com.achers.ascmake.proxys.web;

import android.util.Log;
import android.webkit.WebView;

import com.achers.ascmake.proxys.web.Iweb;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * author：lhm on 2018/7/2 17:48
 * <p>
 * email：3186834196@qq.com
 */
public class WebViewProxy  implements InvocationHandler {

    private WebView iweb;
    private Iweb proxy;



    public WebViewProxy(WebView iwebs){
        this.iweb=iwebs;
        this.proxy= (Iweb) Proxy.newProxyInstance(Iweb.class.getClassLoader(),new Class<?>[] { Iweb.class },this);
    }

    public Iweb getIwebProxy(){
        return proxy;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName = method.getName();

       Log.e("proxyweb","===========starting invoke function:" + methodName + "==========");

        Object result = method.invoke(iweb, args);

        Log.e("proxyweb","=========== invoke function:" + methodName + " success==========");
        return result;
    }
}
