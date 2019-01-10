package com.achers.ascmake.components.componentWeb;

import android.content.Context;
import android.view.KeyEvent;

/**
 * Created on 2018/7/11 15:25
 * <p>
 * author lhm
 * <p>
 * Description:
 * <p>
 * Remarks:
 */
public interface IWeb {

    void loadUrl(String url);


    void setWebViewClient();


    void payAction();

    void onKeyDownWeb(int keyCode, KeyEvent event);
}
