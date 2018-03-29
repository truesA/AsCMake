package com.achers.ascmake;

import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void test() throws ParseException {
        boolean ss=DataUttil.IsToday("2018-03-17 21:10:30");
        boolean dd=DataUttil.IsTomorrowday("2018-03-17 21:10:30");
        boolean bb=DataUttil.IsYesterday("2018-03-17 21:10:30");
        System.out.print(ss);
        System.out.print(dd);
        System.out.print(bb);
       String ee = DataUttil.getTitleDay("2018-03-17 21:10:30");
        System.out.print(ee);

    }


    @Test
    public void dealStringRed() {
        String betNumbersred="DQ2|03,04,05-01,04,05";
        int redindex= betNumbersred.indexOf("|");
        String reds= betNumbersred.substring(redindex+1,betNumbersred.length());
        System.out.println(reds);
        String[] lastred=reds.split("-");
        System.out.println(lastred[0]+"--"+lastred[1]);
    }
    /**
     * 子model 拼接String 红
     * @param
     */
    @Test
    public void dealSsqDlt7lcStringRed() {
        String betNumbersred="27,28$01,05,06,17,29,33|03";
      ///  String lastRed=null;
        if (betNumbersred.contains("$")){//包含胆码
        //    System.out.print(betNumbersred);
          //  String[] danString =betNumbersred.split("$");
            int danindex =betNumbersred.indexOf("$");
            System.out.println(danindex);
            String dans =betNumbersred.substring(0,5);//胆
            System.out.println(dans);

            //红球
           int redindex= betNumbersred.indexOf("|");
           String reds= betNumbersred.substring(danindex+1,redindex);
            System.out.println(reds);

            //蓝球
            String blues= betNumbersred.substring(redindex+1,betNumbersred.length());
            System.out.println(blues);


//            String redString =betNumbersred.substring(betNumbersred.indexOf("$"),betNumbersred.indexOf("|"));
//            lastRed=danString+";"+redString;
//        }else{
//            String[] redString =  betNumbersred.split("|");
//            lastRed=redString[0];
        }

     //   return lastRed;
    }
}