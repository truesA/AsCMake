package com.achers.ascmake.bamboy;

import android.graphics.Bitmap;


import java.util.List;

/**
 * Create on 2017/6/13 下午5:32
 * <p>
 * author wang
 * <p>
 * Description: 全局的交换区, 实在没有办法的时候才能用
 * <p>
 * Remark:
 */
public class Swap {

    private boolean isShowRecharge;
    private Bitmap bitmap;
//    public int orderTabIndicator = 0;

    public void setIsShowRecharge(boolean isShowRecharge) {
        this.isShowRecharge = isShowRecharge;
    }

    public boolean isShowRecharge() {
        return isShowRecharge;
    }



    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }



    private static class Single {
        private static final Swap swap = new Swap();
    }

    private Swap() {
    }

    public static Swap getInstance() {
        return Single.swap;
    }




}