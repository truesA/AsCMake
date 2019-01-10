package com.achers.ascmake.proxys.web;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.achers.ascmake.R;
import com.achers.ascmake.proxys.web.ProxyWebActivity;

public class ProxyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proxy);
    }


    public void toGo(View view){
        Intent intent= new Intent();
        intent.setClass(this,ProxyWebActivity.class);
        startActivity(intent);
    }
}
