package com.zhou.bot.zhoubottest.threadtest;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @ClassName CyclicBarrierThread
 * @Description 描述这个类作用
 * @Author ZhouYouMing
 * @Date 2019/4/10 20:39
 */
public class CyclicBarrierThread {
    public static void main(String[] args){
        final CyclicBarrier cb = new CyclicBarrier(3);
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("t1......");
                    Long k = 0L;
                    while (true){
                        k++;
                        if(k.equals(10000000L)){
                            break;
                        }
                    }
                    cb.await();
                    System.out.println("t1---->");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("t2......");
                    Long k = 0L;
                    while (true){
                        k++;
                        if(k.equals(100000000L)){
                            break;
                        }
                    }
                    cb.await();
                    System.out.println("t2---->");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("t3......");
                    Long k = 0L;
                    while (true){
                        k++;
                        if(k.equals(1000000000L)){
                            break;
                        }
                    }
                    cb.await();
                    System.out.println("t3---->");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();t2.start();t3.start();
    }
}
