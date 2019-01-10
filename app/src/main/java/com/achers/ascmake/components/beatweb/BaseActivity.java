package com.achers.ascmake.components.beatweb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.achers.ascmake.R;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }
}
