package com.zhou.bot.zhoubottest.threadtest;

/**
 * @ClassName MyThread30_0
 * @Description 描述这个类作用
 * @Author ZhouYouMing
 * @Date 2019/3/27 20:18
 */
public class MyThread30_0  extends  Thread{

    private Object object;

    public MyThread30_0(Object o){
        this.object = o;
    }

    public void run(){
        try {
            synchronized (object){
                System.out.println("MyThread30_0开始执行,下面将进入等待");
                object.wait();
                //Thread.yield();
                /*Long k = 0L;
                while (true){
                    k++;
                    if(k.equals(1000000000L)){
                        break;
                    }
                }*/
                System.out.println("MyThread30_0有人唤醒了，我接着执行:"+Thread.currentThread().getState());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
