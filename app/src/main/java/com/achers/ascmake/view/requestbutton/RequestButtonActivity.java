package com.achers.ascmake.view.requestbutton;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.achers.ascmake.MainActivity;
import com.achers.ascmake.R;
import com.achers.ascmake.view.customstatusview.CustomStatusView;

public class RequestButtonActivity extends AppCompatActivity implements View.OnClickListener {
    RequestButton end2;

    private Button btn_init;
    private Button btnSuccess;
    private Button btnFailure;
    private CustomStatusView customStatusView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_button);

         end2 =findViewById(R.id.end2);


        end2.setOnRequestCallback(new OnRequestCallback() {
            @Override
            public boolean beforeRequest() {
                return true;
            }

            @Override
            public void onRequest() {
                end2.requestSuccess();
//               end2.requestFailure();
                Toast.makeText(RequestButtonActivity.this, "finishisSuccess", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFinish(boolean isSuccess) {
//                end2.requestFailure();
                Toast.makeText(RequestButtonActivity.this, "finish"+isSuccess, Toast.LENGTH_SHORT).show();
            }
        });

        customStatusView = (CustomStatusView) findViewById(R.id.as_status);
        btn_init = (Button) findViewById(R.id.btn_init);
        btnSuccess = (Button) findViewById(R.id.btn_success);
        btnFailure = (Button) findViewById(R.id.btn_failure);

        //customStatusView.loadLoading();
        btnSuccess.setOnClickListener(this);
        btnFailure.setOnClickListener(this);
        btn_init.setOnClickListener(this);


    }

    public void success(View view){
        end2.requestSuccess();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_success:
                customStatusView.loadSuccess();
                break;
            case R.id.btn_failure:
                customStatusView.loadFailure();
                break;
            case R.id.btn_init:
                customStatusView.loadLoading();
                break;
        }
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


    public void gogo(View view){
        Intent intent=new Intent();
        intent.setClass(this, MainActivity.class);
        startActivity(intent);
    }
}
