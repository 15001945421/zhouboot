package com.zhou.bot.zhoubottest.otest;

import java.math.BigDecimal;
import java.util.Calendar;

/**
 * @ClassName T4
 * @Description 描述这个类作用
 * @Author ZhouYouMing
 * @Date 2019/9/30 17:02
 */
public class T4 {

    public static void t(){
        Calendar now = Calendar.getInstance();

        //根据配置获取一段时间内的红包
        int hbMonth = 2;
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH);
        //System.out.println(month);
        //分区 hbMonth =1一年，=2半年，=4一个季度，=12一个月
        int partition = 12 / hbMonth;
        int start = month / partition * partition;
        //System.out.println(month / partition);
        System.out.println(start);

        //for(int m=hbMonth;m>0;m--){
        //    System.out.println(month--);
        //}
    }
    public static void main(String[] args){

        t();
    }
}
