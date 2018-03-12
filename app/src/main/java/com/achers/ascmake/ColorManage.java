package com.achers.ascmake;

import android.content.Context;
import android.util.Log;

/**
 * Create on 2017/12/28 18:31
 * <p>
 * author lhm Achers
 * <p>
 * Description:
 * <p>
 * Version: 1.2.3
 */
public  class ColorManage {

    private Context context;

    public void init(Context context){
        this.context=context;
    }


    public static int setTextColorAs(Context context,int color){
        Log.e("color",color+"");
      //  if ()
     return  context.getResources().getColor(color);

    }
}
