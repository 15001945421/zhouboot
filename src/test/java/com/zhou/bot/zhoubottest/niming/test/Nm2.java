package com.zhou.bot.zhoubottest.niming.test;

/**
 * @ClassName Nm2
 * @Description 匿名内部类
 * @Author ZhouYouMing
 * @Date 2019/7/30 9:47
 */
public class Nm2 {

    public void n2(String s,Nm1 nm1){
        nm1.t1(s);
    }

    public static void main(String[] args){
        final String s = "hello";
        new Nm2().n2(s, new Nm1() {
            @Override
            public void t1(String s) {
                System.out.println(0!=0);
            }
        });
    }
}
