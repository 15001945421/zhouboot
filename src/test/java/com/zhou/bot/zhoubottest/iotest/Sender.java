package com.zhou.bot.zhoubottest.iotest;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * @ClassName Sender
 * @Description 描述这个类作用
 * @Author ZhouYouMing
 * @Date 2019/3/26 20:10
 */
public class Sender extends Thread{

    //管道输出流
    private PipedOutputStream pipe = new PipedOutputStream();

    public PipedOutputStream getPipedOutputStream(){
        return pipe;
    }

    public void run(){
        String str = "你好，sender";
        try {
            pipe.write(str.getBytes());//像管道流中写入数据
            pipe.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
