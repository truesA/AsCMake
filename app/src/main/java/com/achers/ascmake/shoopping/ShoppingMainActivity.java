package com.achers.ascmake.shoopping;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.achers.ascmake.MainActivity;
import com.achers.ascmake.R;
import com.achers.ascmake.shoopping.adapter.CartExpandAdapter;
import com.achers.ascmake.shoopping.callback.OnClickAddCloseListenter;
import com.achers.ascmake.shoopping.callback.OnClickDeleteListenter;
import com.achers.ascmake.shoopping.callback.OnClickListenterModel;
import com.achers.ascmake.shoopping.callback.OnViewItemClickListener;
import com.achers.ascmake.shoopping.entity.CartInfo;
import com.achers.ascmake.shoopping.util.DataList;
import com.alibaba.fastjson.JSON;

/**
 * Created on 2019/3/11 22:41
 * <p>
 * author lhm
 * <p>
 * Description:
 * <p>
 * Remarks:
 */
public class ShoppingMainActivity extends AppCompatActivity {


    ExpandableListView cartExpandablelistview;
    TextView cartNum;
    TextView cartMoney;
    Button cartShoppMoular;

    CartInfo cartInfo;
    CartExpandAdapter cartExpandAdapter;
    double price;
    int num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_fragmentn_layout);

        cartExpandablelistview = findViewById(R.id.cart_expandablelistview);
        cartNum = findViewById(R.id.cart_num);
        cartMoney = findViewById(R.id.cart_money);
        cartShoppMoular = findViewById(R.id.cart_shopp_moular);
        initView();
    }

    private void initView() {
        cartExpandablelistview.setGroupIndicator(null);
        showData();

        cartShoppMoular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ShoppingMainActivity.this, "提交订单:  " + cartMoney.getText().toString() + "元", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void showData() {
        cartInfo = JSON.parseObject(DataList.JSONDATA(), CartInfo.class);
        if (cartInfo != null && cartInfo.getData().size() > 0) {
            cartExpandAdapter = null;
            showExpandData();
        } else {
            try {
                cartExpandAdapter.notifyDataSetChanged();
            } catch (Exception e) {
                return;
            }
        }
    }

    private void showExpandData() {
        cartExpandAdapter = new CartExpandAdapter(this, cartExpandablelistview, cartInfo.getData());
        cartExpandablelistview.setAdapter(cartExpandAdapter);
        int intgroupCount = cartExpandablelistview.getCount();
        for (int i = 0; i < intgroupCount; i++) {
            cartExpandablelistview.expandGroup(i);
        }
        /**
         * 全选
         */
        cartExpandAdapter.setOnItemClickListener(new OnViewItemClickListener() {
            @Override
            public void onItemClick(boolean isFlang, View view, int position) {
                cartInfo.getData().get(position).setIscheck(isFlang);
                int length = cartInfo.getData().get(position).getItems().size();
                for (int i = 0; i < length; i++) {
                    cartInfo.getData().get(position).getItems().get(i).setIscheck(isFlang);
                }
                cartExpandAdapter.notifyDataSetChanged();
                showCommodityCalculation();
            }
        });

        /**
         * 单选
         */
        cartExpandAdapter.setOnClickListenterModel(new OnClickListenterModel() {
            @Override
            public void onItemClick(boolean isFlang, View view, int onePosition, int position) {
                cartInfo.getData().get(onePosition).getItems().get(position).setIscheck(isFlang);
                int length = cartInfo.getData().get(onePosition).getItems().size();
                for (int i = 0; i < length; i++) {
                    if (!cartInfo.getData().get(onePosition).getItems().get(i).ischeck()) {
                        if (!isFlang) {
                            cartInfo.getData().get(onePosition).setIscheck(isFlang);
                        }
                        cartExpandAdapter.notifyDataSetChanged();
                        showCommodityCalculation();
                        return;
                    } else {
                        if (i == (length - 1)) {
                            cartInfo.getData().get(onePosition).setIscheck(isFlang);
                            cartExpandAdapter.notifyDataSetChanged();
                        }
                    }
                }
                showCommodityCalculation();
            }
        });
        cartExpandAdapter.setOnClickDeleteListenter(new OnClickDeleteListenter() {
            @Override
            public void onItemClick(View view, int onePosition, int position) {

                //具体代码没写， 只要删除商品，刷新数据即可
                Toast.makeText(ShoppingMainActivity.this, "删除操作", Toast.LENGTH_LONG).show();
            }
        });

        /***
         * 数量增加和减少
         */
        cartExpandAdapter.setOnClickAddCloseListenter(new OnClickAddCloseListenter() {
            @Override
            public void onItemClick(View view, int index, int onePosition, int position, int num) {
                if (index == 1) {
                    if (num > 1) {
                        cartInfo.getData().get(onePosition).getItems().get(position).setNum((num - 1));
                        cartExpandAdapter.notifyDataSetChanged();
                    }
                } else {
                    cartInfo.getData().get(onePosition).getItems().get(position).setNum((num + 1));
                    cartExpandAdapter.notifyDataSetChanged();
                }
                showCommodityCalculation();
            }
        });
        showCommodityCalculation();
    }

    private void showCommodityCalculation() {
        price = 0;
        num = 0;
        for (int i = 0; i < cartInfo.getData().size(); i++) {
            for (int j = 0; j < cartInfo.getData().get(i).getItems().size(); j++) {
                if (cartInfo.getData().get(i).getItems().get(j).ischeck()) {
                    price += Double.valueOf((cartInfo.getData().get(i).getItems().get(j).getNum() * Double.valueOf(cartInfo.getData().get(i).getItems().get(j).getPrice())));
                    num++;
                }
            }
        }
        if (price == 0.0) {
            cartNum.setText("共0件商品");
            cartMoney.setText("¥ 0.0");
            return;
        }
        try {
            String money = String.valueOf(price);
            cartNum.setText("共" + num + "件商品");
            if (money.substring(money.indexOf("."), money.length()).length() > 2) {
                cartMoney.setText("¥ " + money.substring(0, (money.indexOf(".") + 3)));
                return;
            }
            cartMoney.setText("¥ " + money.substring(0, (money.indexOf(".") + 2)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
