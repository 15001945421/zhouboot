package com.zhou.bot.zhoubottest.threadtest.futuretest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @ClassName FutureR1
 * @Description 描述这个类作用
 * @Author ZhouYouMing
 * @Date 2019/8/9 19:46
 */
public class FutureT1  {

    public void t1() throws Exception{
        ExecutorService executorService= Executors.newCachedThreadPool();
        List<Future<String>> futureList = new ArrayList<Future<String>>();
        Callable c1 = (Callable) new FutureR1();
        Future f1 = executorService.submit(c1);
        futureList.add(f1);

        Callable c2 = (Callable) new FutureR2();
        Future f2 = executorService.submit(c2);
        futureList.add(f2);

        for(Future kl : futureList){
            System.out.println(kl.get());
        }
    }

    public static void main(String[] args){
        try {
            new FutureT1().t1();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
