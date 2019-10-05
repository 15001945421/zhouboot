package com.zhou.bot.zhoubottest.threadtest;

import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName CountDownLatch_T1
 * @Description 描述这个类作用
 * @Author ZhouYouMing
 * @Date 2019/4/10 21:01
 */
public class CountDownLatch_T1 {


    public static void main(String[] args) throws Exception{
        CountDownLatch cdl = new CountDownLatch(3);
        new Test1T21(cdl).start();
        new Test1T22(cdl).start();
        cdl.await(4500, TimeUnit.MILLISECONDS);
        System.out.println("Success...");
    }

}
