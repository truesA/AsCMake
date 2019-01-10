package com.achers.ascmake.components;

import android.util.Log;

/**
 * Created on 2018/7/10 15:51
 * <p>
 * author lhm
 * <p>
 * Description:
 * <p>
 * Remarks:
 */
public class ConcreteDecoratorA extends Decorator {

    public ConcreteDecoratorA(Component component) {
        super(component);
    }


    @Override
    public void loadUrl() {

        loadUrlBefore();
        super.loadUrl();
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
