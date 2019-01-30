package com.achers.ascmake.arout;

import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.achers.ascmake.MainActivity;
import com.achers.ascmake.R;
import com.achers.ascmake.calendar.CalendarViewMainActivity;
import com.achers.ascmake.recyclers.RecyclerMainActivity;
import com.achers.ascmake.slidemenu.SlideMenuActivity;
import com.alibaba.android.arouter.launcher.ARouter;

import java.math.BigDecimal;

public class AroutActivity extends AppCompatActivity {
    EditText editText;
    ImageView imageview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arout);

        TextView textView =findViewById(R.id.text_html);
         imageview =findViewById(R.id.iv_f);
         editText =findViewById(R.id.et);
//        String value ="<p>rnt<span style=\\\"font-family:sans-serif;font-size:16px;\\\"><span style=\\\"font-family:sans-serif;font-size:16px;\\\"><span style=\\\"font-family:sans-serif;font-size:16px;\\\"><span style=\\\"font-family:sans-serif;font-size:16px;\\\">日升昌票号的前身是西裕成颜料庄，总庄设在平遥，并在北京崇文门外设有分庄。清嘉庆末年，由于社会商品货币经济的发展，埠际间货币流通量大增，而过去的起标运银由于很不安全，已 不能适应新形势的需要，西裕成颜料庄首先在京、晋间试行汇兑办法，结果效果很好，便开始兼营汇兑业。
// 道光初年，西裕成颜料庄正式更名为日升昌票号，专营汇兑山西平遥日升昌票号。 日
// 升昌位于平遥城内西大街路南。票号旧址至今保存完好。坐南朝北，并列两院，南北
// 进深65米，建筑面积1300平方米。三进院落。临街面宽5间，厚木排门，檐下彩画，
// 挂店名牌横匾，院屋第一进为柜台、账房，东西两边各有柜房2间。二进东西各建客房3间
// 。正面为中厅，面阔三间，为汇兑业务室，其上建有楼房，用以存放物品。紧靠中厅南檐接出半
// 坡顶平房3间,为职员住处。中间为走道，东西两边为小套间，楼上为仓贮和伙计住处。后院南向
// 有正厅5间，东西各有客房3间，是贵宾及高级职员住处。西侧有廊道可通马车，备确马厩和马倌住处
// 。整个院落墙高宅深，布局紧凑，设计精巧，上空架设有铁丝天网，网上系有响铃。临街铺面5扇坚
// 实大门一关，极为安全。平遥票号　平遥古城的票号是中国金融发展史上的重要里程碑。由于平遥票号创立时间
// 之早，延续年代之长，票号数量之多，网点分布之广，资本实力之雄厚，因而，一度执金融之牛耳，闻名于海内外。陈运
// 和到此以诗回忆：“金融之 产婆接生于平遥 清末之繁华进出于票号 道光三年，日升昌问世的啼叫 引天下瞩目（包括
// 一个衰败的王朝） 气宇昂然是财神 临街</span></span></span></span>\\r\\n</p >\\r\\n<p>\\r\\n\\
// t<span style=\\\"font-family:sans-serif;font-size:16px;\\\"><span style=\\\"font-fa
// mily:sans-serif;font-size:16px;\\\"><span style=\\\"font-family:sans-serif;font-siz
// e:16px;\\\"></span></span></span> \\r\\n</p >\\r\\n<p style=\\\"text-align:center;\\\
// ">\\r\\n\\t<span style=\\\"font-family:sans-serif;font-size:16px;\\\">< img src=\\\"htt
// p://py.codeboor.com/public/uploads/images/20180522/1526980000304809.jpg\\\" title=\\\"1
// 526980000304809.jpg\\\" alt=\\\"21.jpg\\\" /><br />\\r\\n</span> \\r\\n</p >";
//
//
//        textView.setText(Html.fromHtml(value));
        String values="\"<span  style=\\\"font-size: 14px;color:#F8A653;padding:3px 8px;border:1px solid #F8A653;display: inline-block;border-radius: 3px ;\\\">投票</span>\"";
        textView.setText(Html.fromHtml(values));


        imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityOptionsCompat compat =
                        ActivityOptionsCompat.makeSceneTransitionAnimation(AroutActivity.this,
                                imageview, getString(R.string.image));
                ActivityCompat.startActivity(AroutActivity.this, new Intent(AroutActivity.this,
                        MainActivity.class), compat.toBundle());
            }
        });

    }




    public void goArout(View view){
        Intent intent =new Intent(this, RecyclerMainActivity.class);
        startActivity(intent);
//        ARouter.getInstance()
//                .build("/tests/test")
//                .navigation();
//        double values=Double.valueOf(editText.getText().toString());
//       // double values=216.66;
//        String last=sciCal(values,2);
//        Log.e("四舍六入五成双计算法 : ",last);

    }

    public void goCalendarView(View view){
        Intent intent =new Intent(this, CalendarViewMainActivity.class);
        startActivity(intent);
    }

    public void goSlideMenu(View view){
        Intent intent =new Intent(this, SlideMenuActivity.class);
        startActivity(intent);
    }

     /**
     * @param value 需要科学计算的数据
     * @param digit 保留的小数位
     * @return
     * 功能：四舍六入五成双计算法
     */
    public static String sciCal(double value, int digit){
        String result = "-999";
        try {
            double ratio = Math.pow(10, digit);
            double _num = value * ratio;
                    //* ratio;
            double mod = _num % 1;
            double integer = Math.floor(_num);
            double returnNum;
            if(mod > 0.5){
                returnNum=(integer + 1) / ratio;
                       // /
            }else if(mod < 0.5){
                returnNum=integer/ ratio;
                        //
            }else{
                returnNum=(integer % 2 == 0 ? integer : integer + 1);
                // ratio;
            }
            BigDecimal bg = new BigDecimal(returnNum);
            result = bg.setScale((int)digit, BigDecimal.ROUND_HALF_UP).toString();
        } catch (RuntimeException e) {
            throw e;
        }
        return result;
    }

}
