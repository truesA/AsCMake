package com.achers.ascmake.components;

import android.util.Log;

/**
 * Created on 2018/7/10 16:03
 * <p>
 * author lhm
 * <p>
 * Description:
 * <p>
 * Remarks:
 */
public class ConcreteDecoratorB extends Decorator{

    public ConcreteDecoratorB(Component component) {
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
        Log.e("ConcreteDecoratorB","加头");
    }

    /**
     * 加载之后
     */
    private void loadUrlAfter(){
        //防止泄露和发送反差消息
        Log.e("ConcreteDecoratorB","防止泄露和发送反差消息");
    }

}
