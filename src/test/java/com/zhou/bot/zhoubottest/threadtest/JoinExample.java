package com.zhou.bot.zhoubottest.threadtest;

/**
 * @ClassName JoinExample
 * @Description 描述这个类作用
 * @Author ZhouYouMing
 * @Date 2019/3/29 20:16
 */
public class JoinExample {

    public static void main(String[] args) throws Exception{

        //关于join参考链接：https://www.cnblogs.com/skywang12345/p/3479275.html
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("First task started-t1");
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    System.out.println("First task started-t2");
                    t1.start();
                    t1.join();
                    System.out.println("First task started-t2-2");
                    Long i = 0L;
                    for(;;){
                        i++;
                        if(i.equals(100000000L)){
                            break;
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t2.start();
        //Thread.sleep(100);
        //t1.start();
        //t1.join();

        //System.out.println("main-o");
    }
}
