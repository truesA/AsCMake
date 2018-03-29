package com.achers.ascmake.supertextviewactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.achers.ascmake.R;
import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

public class MaterialDialogsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_dialogs);
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

    }
}
