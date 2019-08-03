package com.zhou.bot.zhoubottest.otest;

import java.text.MessageFormat;

/**
 * @ClassName Sta
 * @Description 描述这个类作用
 * @Author ZhouYouMing
 * @Date 2019/4/18 20:20
 */
public class Sta {
    public  Sta(int k){
        System.out.println("sta-"+k);
    }

    public static  String  sss(String ss,Object ... ob){
        //String ss = "pages/quiz/views/assistance/index?groupId={0}&act={1}";
        Object[] oo = ob;
        String[] df = new String[oo.length];
        for(int k=0;k<oo.length;k++){
            df[k] = String.valueOf(oo[k]);
        }

        return  MessageFormat.format(ss,df);
    }

    public static void main(String[] args){
     String ss = "pages/quiz/views/assistance/index?groupId={0}&act={1}";
     //System.out.println(MessageFormat.format(ss,123265864325L+"",456+"scdfg"));
        System.out.println(sss(ss,123265864,456+"scdfg"));
    }
}
