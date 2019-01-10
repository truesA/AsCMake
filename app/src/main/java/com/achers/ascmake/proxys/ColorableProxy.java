package com.achers.ascmake.proxys;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * author：lhm on 2018/7/2 17:22
 * <p>
 * email：3186834196@qq.com
 */
public class ColorableProxy implements InvocationHandler {

    private Colorable colorable;
    private Colorable proxy;

    public ColorableProxy(Colorable colorable) {
        this.colorable = colorable;
        this.proxy = (Colorable) Proxy.newProxyInstance(Colorable.class.getClassLoader(), new Class<?>[] { Colorable.class }, this);
    }

    public Colorable getProxy() {
        return proxy;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        String methodName = method.getName();

        System.out.println("===========starting invoke function:" + methodName + "==========");

        Object result = method.invoke(colorable, args);

        System.out.println("=========== invoke function:" + methodName + " success==========");
        return result;
    }

}
