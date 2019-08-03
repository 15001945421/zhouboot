package com.zhou.bot.zhoubottest.threadtest;

/**
 * @ClassName MyThread30_0
 * @Description 描述这个类作用
 * @Author ZhouYouMing
 * @Date 2019/3/27 20:18
 */
public class MyThread30_1 extends  Thread{

    private Object object;

    public MyThread30_1(Object o){
        this.object = o;
    }

    public void run(){
        try {
            synchronized (object){
                System.out.println("MyThread30_1开始执行,因为我获取到锁了");
                object.notify();
                Long k = 0L;
                while (true){
                    k++;
                    if(k.equals(100000000L)){
                        break;
                    }
                }
                System.out.println("MyThread30_1唤醒其他人吧:"+Thread.currentThread().getState());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
