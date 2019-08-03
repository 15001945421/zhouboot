package com.zhou.bot.zhoubottest.utils;

import java.text.MessageFormat;

/**
 * @ClassName ParamFormatUtils
 * @Description 描述这个类作用
 * @Author ZhouYouMing
 * @Date 2019/4/25 19:53
 */
public class ParamFormatUtils {

    public static  String  stringFormat(String str,Object ... ob){
        Object[] oo = ob;
        String[] df = new String[oo.length];
        for(int k=0;k<oo.length;k++){
            df[k] = String.valueOf(oo[k]);
        }

        return  MessageFormat.format(str,df);
    }

    public static void main(String[] args){
        String ss = "助力已完成，成功获得{0}金币，金币将在活动结束后24小时内发放至您的1号店账户。";
        System.out.println(stringFormat(ss,250));
        final Integer dd = 123;
        System.out.println(MessageFormat.format(ss,String.valueOf(dd)));
        Long s = 90L;
        System.out.println(s/60);
        //char[] ch = {'1','2'};
        //System.out.println(String.valueOf(ch));

    }
}
