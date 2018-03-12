package com.achers.ascmake;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.achers.ascmake.timeview.LTimeView;
import com.achers.ascmake.web.WebAsActivity;
import com.achers.ascmake.web.WebChonzhiAcvitity;
import com.achers.ascmake.web.WebsActivity;
import com.tuyenmonkey.textdecorator.TextDecorator;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    private EditText editText;
    private LTimeView lTimeView;
    private TextView textss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        TextView tv = (TextView) findViewById(R.id.sample_text);
        tv.setText(stringFromJNI());
        tv.setOnClickListener(v -> {
            Toast.makeText(this, "lambda", Toast.LENGTH_SHORT).show();
        });
//        final List<String> list = new ArrayList<String>();
//        list.add("已下单");
//        list.add("已付款");
//        list.add("已发货");
//        list.add("已收货");
//        list.add("已评价");
//        final StateProgressView stateProgressView01 = (StateProgressView) findViewById(R.id.spv_01);
//
//        stateProgressView01.setItems(list, 4, 200);

        textss=findViewById(R.id.texts);

        editText = findViewById(R.id.et);
        lTimeView = findViewById(R.id.lt);

        String[] ssa=getDefultNumArray(ss);

        Log.e("getSScArraysout",getSScArraysout(ssa));


        final String text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis et porta ipsum. Praesent vitae libero a mi sodales accumsan. Donec tempor nulla turpis, vitae pellentesque ligula consectetur sed. Quisque commodo lorem eget ipsum pulvinar consequat. Nam erat risus, rhoncus quis ligula sed, tempor venenatis nulla. Duis quis placerat quam.";

//        TextDecorator
//                .decorate(textss, text)
//                .setTextColor(R.color.colorAccent, "dolor", "1234","","5678")
//                .setTextColor(R.color.colorAccent,"dolor")
//                .setBackgroundColor(R.color.colorPrimary, "dolor", "elit")
//                .strikethrough("Duis", "Praesent")
//                .underline("sodales", "quam")
//                .setSubscript("vitae")
//                .makeTextClickable(new OnTextClickListener() {
//                    @Override public void onClick(View view, String text) {
//                        Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
//                    }
//                }, false, "porta", "commodo", "tempor venenatis nulla")
//                .setTextColor(android.R.color.holo_green_light, "porta", "commodo", "tempor venenatis nulla")
//                .build();

        String a=ones.charAt(ones.length()-1)+"";

        String b =""+ones.charAt(ones.length()-2)+ones.charAt(ones.length()-1);

        String c =ones.charAt(ones.length()-3)+""+ones.charAt(ones.length()-2)+ones.charAt(ones.length()-1);

        String d=ones.charAt(0)+"";

        String e=ones.charAt(0)+""+ones.charAt(1);

        String f=ones.charAt(0)+""+ones.charAt(1)+ones.charAt(2);

        Log.e("ones",a+"--"+b+"--"+c+"--"+d+"--"+e+"--"+f);


        String te="06,15,17,18,23,30|11";



        String re="开奖号码: 06,15,17,18,23,30|11";

      String[] strings={"x","x","x","x","x","x","x","x","x","x"};
        //String[] strings=new String[10];
       // String ss1=strings[0]="0";
       // String ss6=strings[6]="6";
      //  String ss9=strings[9]="1";
        String zzz="2894";
        String ggg="127894";

        int k=0;
        for (int i=0;i<zzz.length();i++){
           if ( ggg.contains(zzz.charAt(i)+"")){
               if (strings[k]=="x"){
                   strings[k]=zzz.charAt(i)+"";
               }else {
                   strings[k+1]=zzz.charAt(i)+"";
                   k=k+1;
               }

              // arrayList.add(zzz.charAt(i)+"");
           }
        }

//        for (int i=0;i<arrayList.size();i++){
//            strings[i]=arrayList.get(i);
//        }
        Log.e("arrayList",strings[0]+strings[1]);

//        Log.e("arrayList",arrayList.toString()+"-----"+arrayList.get(0)+"-----"+arrayList.get(1));

        TextDecorator.decorate(textss," 0 1 2 8 9 6 5 4")
                .setTextColor(R.color.colorAccent,strings[0],strings[1],strings[2],strings[3],strings[5]
                       )
                    .build();


          String s =  te.replace('|',',');

          String trs=getchangeString(re);

          int   trssin = Integer.parseInt("00");
        Log.e("trssin",trssin+"");



        String[] as =getDefultNumArray("P3|56,23,01");

        Log.e("getSScArraysout(as)",getSScArraysout(as));

        String aaa =changeOneZX("056");
        Log.e("aaa",aaa);
    }
    private String changeOneZX(String ones){
        StringBuffer sb=new StringBuffer();
        for (int i=0;i<ones.length();i++){
            sb=sb.append(ones.charAt(i)+" ");
            //sb=sb;
        }
        return sb.toString();
    }


    private String getchangeString(String tvResult){
        String tvResultnull="";
        if (tvResult.contains("|")){
            tvResultnull=tvResult.replace('|',',');
        }
        tvResultnull=tvResultnull.replace(',',' ');
        return tvResultnull;
    }

    private String ones="12345";


    private String ss = "P3|5";

    /**
     * 获取原始投注数据
     *
     * @param payslip
     * @return
     */
    private String[] getDefultNumArray(String payslip) {
        String[] defultNum = payslip.split("\\|");


        String tuo = defultNum[1];
        Log.e("tuo",tuo);
        // return tuo;

        Log.e("tuo.split(\",\")", Arrays.toString(tuo.split(","))  );
        return tuo.split(",");
    }

    private String getSScArraysout(String[] array) {

        String defultNums = "";
        for (int i = 0; i < array.length; i++) {
            defultNums = defultNums+array[i] + " ";
        }

        return defultNums;

    }

    public void set(View view) {
       // lTimeView.setStatus(Integer.parseInt(editText.getText().toString()));
        Intent intent =new Intent(this,WebsActivity.class);
        startActivity(intent);
    }

    public void gets(View view) {
        // lTimeView.setStatus(Integer.parseInt(editText.getText().toString()));
        Intent intent =new Intent(this,WebAsActivity.class);
        startActivity(intent);
    }

    public void recy(View view) {
        // lTimeView.setStatus(Integer.parseInt(editText.getText().toString()));
        Intent intent =new Intent(this,RecyviewTxActivity.class);
        startActivity(intent);
    }

    public void web(View view){
        Intent intent =new Intent(this, PopWin.class);
        startActivity(intent);
    }




    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}
