package com.zhou.bot.zhoubottest.otest;

/**
 * @ClassName StaticTerst
 * @Description 描述这个类作用
 * @Author ZhouYouMing
 * @Date 2019/4/18 20:12
 */
public class StaticTerst {
    private static  Sta c = new Sta(1);
    static {
        System.out.println("s");
        c = new Sta(0);
    }

    public static void main(String[] args){
        new StaticTerst();
    }
}
