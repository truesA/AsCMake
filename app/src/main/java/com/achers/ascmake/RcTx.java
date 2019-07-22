package com.achers.ascmake;

/**
 * Create on 2018/2/2 14:40
 * <p>
 * author lhm
 * <p>
 * Description:
 * <p>
 * Version: 1.2.3
 */
public class RcTx {
    private String num;
    private boolean isred;

    public RcTx(String num,boolean isred){
        this.num=num;
        this.isred=isred;


    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public boolean isIsred() {
        return isred;
    }

    public void setIsred(boolean isred) {
        this.isred = isred;
    }
}
