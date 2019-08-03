package com.zhou.bot.zhoubottest.threadtest;

/**
 * @ClassName MainThreadTest
 * @Description 描述这个类作用
 * @Author ZhouYouMing
 * @Date 2019/3/27 20:25
 */
public class MainThreadTest {
    public static void main(String[] args){
        try {
            Object o = new Object();
            MyThread30_0 myThread30_0 = new MyThread30_0(o);
            myThread30_0.start();
            Thread.sleep(1000);
            MyThread30_1 myThread30_1 = new MyThread30_1(o);
            myThread30_1.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
