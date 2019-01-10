package com.achers.ascmake.proxys;

/**
 * author：lhm on 2018/7/2 17:21
 * <p>
 * email：3186834196@qq.com
 */
public class RedColor implements Colorable {

    @Override
    public void value() {
        System.out.println("--------------red-------------");
    }

    @Override
    public void init() {
        System.out.println("--------------init-------------");
    }

}

