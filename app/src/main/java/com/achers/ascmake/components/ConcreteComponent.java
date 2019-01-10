package com.achers.ascmake.components;

import android.util.Log;

/**
 * Created on 2018/7/10 15:47
 * <p>
 * author lhm
 * <p>
 * Description:
 * <p>
 * Remarks: 被修饰者
 */
public class ConcreteComponent extends Component {

    @Override
    public void loadUrl() {
        Log.e("ConcreteComponent","被修饰者");
    }
}
