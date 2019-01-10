package com.achers.ascmake.components.beatweb;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created on 2018/7/11 17:54
 * <p>
 * author lhm
 * <p>
 * Description:
 * <p>
 * Remarks:
 */
public class Param implements Serializable {

    public Param() {
    }

    public Param(String schemaId, String schemaType) {
        this.schemaId = schemaId;
        this.schemaType = schemaType;
    }

    public boolean isModify;
    public String lotteryType;
    public String payslip;
    public boolean isShowShare;

    public String phone;
    public String openid;
    public String id;

    public String userId;

    public String token;

    public String title;
    public String text;

    public String flag;

    public String url;

    public String schemaId;

    public String path;

    public String schemaType = "";

    public String followUpId;

    public String price;

    public String gateWay;

    public String tradeCode;

    public String couponId;

    public String issue;

    public int matchId;

    public String playType;

    public String drawSeq;
    public String times;
    public String bets;
    public String predictPrize;
    public String predictPrizeCopy;


    public ArrayList<String> imageUrlList;

    public int imagePagePosition;

    public String channelId;

    public ArrayList<String> bjdcDrawSeqs;

    public ArrayList<String> leaguesList;


    public boolean headweb;

    public String payType = "";//链接类型（普通h5=h5,微信协议=protocol,表单=form）

    public String filePath;//传递文件路径
}