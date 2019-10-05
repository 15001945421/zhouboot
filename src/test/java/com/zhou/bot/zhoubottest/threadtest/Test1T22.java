package com.zhou.bot.zhoubottest.threadtest;

import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * @ClassName Test1T22
 * @Description 描述这个类作用
 * @Author ZhouYouMing
 * @Date 2019/9/29 18:09
 */
public class Test1T22 extends Thread{

    private CountDownLatch cdl;

    Test1T22(CountDownLatch cdl) {
        this.cdl = cdl;
    }
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(new Date() + "name =" + Thread.currentThread().getName());
        cdl.countDown();
    }
}
