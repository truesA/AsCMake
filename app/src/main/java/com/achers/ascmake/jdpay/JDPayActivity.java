package com.achers.ascmake.jdpay;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.achers.ascmake.MainActivity;
import com.achers.ascmake.R;
import com.jdpaysdk.author.JDPayAuthor;

public class JDPayActivity extends AppCompatActivity {
    private EditText mTv;
    private Button mBtn;
    private EditText mEdt_orderId;
    private EditText mEdt_merchant;
    private EditText mEdt_appKey;
    private EditText mEdt_signData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jdpay);
        mTv = (EditText) findViewById(R.id.tv_show);
        mEdt_merchant = (EditText) findViewById(R.id.edt_merchant);
        mEdt_appKey = (EditText) findViewById(R.id.edt_appKey);
        mEdt_signData = (EditText) findViewById(R.id.edt_signData);
    }

    public void pay(View view){
        JDPayAuthor jdPayAuthor = new JDPayAuthor();
        String orderId = "1006153087760417513111";
        String merchant = "110982166002";
        String appId ="b9f8661ee5a71f5f0893930d29fb3267";
        String signData = "37bf21057c803b89da4e03bf8ac61149";
        jdPayAuthor.author(JDPayActivity.this, orderId, merchant, appId, signData,"");
    }
}
