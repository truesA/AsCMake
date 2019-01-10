package com.achers.ascmake.components.beatweb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.achers.ascmake.R;

public class BaseWebActivity extends AppCompatActivity {


    Param param;
    private String schemaId = "";
    private String successUrl;
    private String errorUrl;
//    private DataManager dataManager;
    private int ismain = 0;// 0 上一页 1=返回app 2指定页面
    private String toUrl;
    private boolean isMianPlace;
    private boolean isNoReload;//登录是否需要重新加载获取用户信息的链接


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_web);
    }
}
