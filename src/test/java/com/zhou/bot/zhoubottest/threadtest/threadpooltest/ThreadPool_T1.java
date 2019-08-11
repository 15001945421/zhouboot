package com.zhou.bot.zhoubottest.threadtest.threadpooltest;

import com.alibaba.fastjson.JSON;

import java.sql.Connection;

/**
 * @ClassName ThreadPool_T1
 * @Description 描述这个类作用
 * @Author ZhouYouMing
 * @Date 2019/8/11 10:56
 */
public class ThreadPool_T1 {
    static ConnTestPool testPool = new ConnTestPool(2);

    static class ThredGet_T1 implements Runnable{

        @Override
        public void run() {
            Connection c1 = testPool.getLianjieConn(5*1000L);
            if(c1==null){
                System.out.println("JSON.toJSONString(c1):null");
                return;
            }
            //System.out.println("JSON.toJSONString(c1)："+c1.getCatalog());
            for(int j=0;j<1000000000;j++){

            }
            testPool.shiFangConn(c1);
        }
    }



    static void t1(){
        //ThredGet_T1 t11 = new ThredGet_T1();
        //ThredGet_T1 t12 = new ThredGet_T1();
        for(int i=0;i<5;i++){
            ThredGet_T1 t11 = new ThredGet_T1();
            Thread thread = new Thread(t11);
            thread.start();
        }
    }

    public static void main(String[] args){
        ThreadPool_T1.t1();
    }

}
