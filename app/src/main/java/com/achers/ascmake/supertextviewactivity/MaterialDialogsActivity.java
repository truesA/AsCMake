package com.achers.ascmake.supertextviewactivity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.achers.ascmake.R;
import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.alibaba.android.arouter.facade.annotation.Route;

import java.util.Arrays;
@Route(path = "/tests/test")
public class MaterialDialogsActivity extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_dialogs);

         textView =findViewById(R.id.md_tv);

    }

    private String[] home6sortlottery(String tagString,String[] defult){
        String indexs=null;
        for (int i=0;i<defult.length;i++){
            if (i<6){
                if (defult[i].equals(tagString)){
                    return defult;
                }
            }else if (defult[i].equals(tagString)){
                indexs= defult[5];
                defult[5]=defult[i];
                defult[i]=indexs;
            }
        }
        return defult;
    }

    public void ck4(View view){
        String[] model={"1","2","3","4","5","6","7","8","9","10","11","12"};
        String[] strings=home6sortlottery("12",model);
        textView.setText(Arrays.toString(strings));
    }


    public void ck(View view){
//        new MaterialDialog.Builder(this)
//                .limitIconToDefaultSize()
//                .title("性别")
//                .positiveText("确定")
//                .negativeText("取消")
//                .onAny(new MaterialDialog.SingleButtonCallback() {
//                    @Override
//                    public void onClick(MaterialDialog dialog, DialogAction which) {
//                        Toast.makeText(MaterialDialogsActivity.this,"Prompt checked? " + dialog.isPromptCheckBoxChecked(),Toast.LENGTH_LONG).show();
//                    }
//                })
//
//                .checkBoxPrompt("男",true, null)
//                //.checkBoxPromptRes(R.string.man, true, null)
////                .checkBoxPromptRes(R.string.woman, true, new CompoundButton.OnCheckedChangeListener() {
////                    @Override
////                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
////                        Toast.makeText(MaterialDialogsActivity.this,"Prompt checked? " + isChecked,Toast.LENGTH_LONG).show();
////                    }
////                })
//                .show();
        new MaterialDialog.Builder(this)
                .title("修改姓名")
                .inputRange(2, 16)
                .positiveText("提交")
                .widgetColor(Color.BLUE)
                .inputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD)
                .input("", "提交", new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(MaterialDialog dialog, CharSequence input) {
                        // Do something
                    }
                }).show();
    }

    public void ck1(View view){
        new MaterialDialog.Builder(this)
                .title("性别")
                .items(R.array.sex)
                .widgetColor(Color.BLUE)
                .itemsCallbackSingleChoice(1, new MaterialDialog.ListCallbackSingleChoice() {
                    @Override
                    public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                        Toast.makeText(MaterialDialogsActivity.this,text,Toast.LENGTH_LONG).show();
                        return true;
                    }
                })
                .positiveText("提交")
                .show();
    }

    public void ck2(View view){
        boolean wrapInScrollView = true;
        MaterialDialog dialog =new MaterialDialog.Builder(this)
                .title("自定义")
                .customView(R.layout.custom_view, wrapInScrollView)
                .positiveText("提交")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(MaterialDialog dialog, DialogAction which) {
                        // TODO
                    }
                })
                .show();
        View views = dialog.getCustomView();
        ImageView iv =views.findViewById(R.id.iv_dialog);
        TextView tv=views.findViewById(R.id.tv_dialog);
        iv.setImageResource(R.drawable.ticket_failed_failed);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MaterialDialogsActivity.this,"点击图片",Toast.LENGTH_LONG).show();
            }
        });

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MaterialDialogsActivity.this,"点击文字",Toast.LENGTH_LONG).show();
            }
        });
    }

    public void ck3(View view) {
        final MaterialDialog dialog = new MaterialDialog.Builder(this)
                .customView(R.layout.custom_view, false)
                .show();
        View customeView = dialog.getCustomView();

        Button button = (Button) customeView.findViewById(R.id.btn_closeCustome);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }
}
