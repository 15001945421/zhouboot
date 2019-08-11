package com.zhou.bot.zhoubottest.threadtest.threadpooltest;

import org.apache.commons.lang3.StringUtils;

import java.sql.Connection;
import java.util.LinkedList;

/**
 * @ClassName ConnTestPool
 * @Description 自制连接池
 * @Author ZhouYouMing
 * @Date 2019/8/11 9:33
 */
public class ConnTestPool {

    private final LinkedList<Connection> que = new LinkedList<>();

    //初始化链接
    public ConnTestPool(int size){
        if(size<=0){
            size = 5;
        }
        for(int i=0;i<size;i++){
            que.addLast(ConnDri.create());
        }

    }

    //释放链接
    public void shiFangConn(Connection connection){
        if(que==null || connection==null){
            return;
        }
        synchronized (que){
            //将释放的链接，加入到队列尾部
            que.addLast(connection);
            //同时通知所有线程，可以取链接了，至于哪一个线程可以渠道，则是不可预知的
            que.notifyAll();
        }
    }

    //获取链接
    public Connection getLianjieConn(Long mile){
        try {
            synchronized (que){
                if(mile<=0){
                    //不需要超时
                    if(que.isEmpty()){
                        //此时队列为空,执行等待，等待链接释放
                        System.out.println("getLianjieConn-isEmpty");
                        que.wait();
                    }
                    System.out.println("getLianjieConn-is-not-Empty");
                    //有链接，那么直接返回链接即可
                    return que.removeFirst();
                }else {
                    //设置了超时

                    //超时时间+当前时间
                    Long et = System.currentTimeMillis()+mile;
                    //需要等待的初始超时时间
                    Long wt = mile;
                    while (wt>0 && que.isEmpty()){
                        //wt>0说明还没有超时,但是没有连接了，怎么办？那就等
                        que.wait(wt);
                        //等待wt时间期间(别的线程通知并释放链接了)或者等待的wt时间到了，则执行下面的代码：et-当前时间

                        wt = et - System.currentTimeMillis();
                        System.out.println("getLianjieConn-wt:"+wt);
                    }
                    System.out.println("getLianjieConn-wt-0000:0000");
                    //存在连接，则在超时时间内取获取链接
                    if(!que.isEmpty()){
                        return que.removeFirst();
                    }
                    return null;
                }

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

}
