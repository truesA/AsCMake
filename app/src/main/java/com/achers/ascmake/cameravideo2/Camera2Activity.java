package com.achers.ascmake.cameravideo2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.achers.ascmake.R;

public class Camera2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera2);
        if (null == savedInstanceState) {
//            getFragmentManager().beginTransaction()
//                    .replace(R.id.container, Camera2BasicFragment.newInstance())
//                    .commit();

//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.container, Camera2VideoFragment.Companion.newInstance())
//                    .commit();
        }
    }
}
