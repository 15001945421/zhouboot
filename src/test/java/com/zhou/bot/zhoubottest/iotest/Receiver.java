package com.zhou.bot.zhoubottest.iotest;

import java.io.IOException;
import java.io.PipedInputStream;

/**
 * @ClassName Receiver
 * @Description 描述这个类作用
 * @Author ZhouYouMing
 * @Date 2019/3/26 20:14
 */
public class Receiver extends Thread {

    private PipedInputStream pipe = new PipedInputStream();

    public PipedInputStream getPipedInputStream(){
        return pipe;
    }

    public void run(){
        String str = "";
        byte[] cs = new byte[1024];
        try {
            int k = pipe.read(cs);
            if(k != -1){
                str = new String(cs, 0 , k);
                System.out.println("收到了以下信息：" + str);
            }
            pipe.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
