package com.zhou.bot.zhoubottest.iotest;

import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * @ClassName MainReceiverSender
 * @Description 描述这个类作用
 * @Author ZhouYouMing
 * @Date 2019/3/26 20:22
 */
public class MainReceiverSender {
    public static void main(String[] args) throws Exception{

        Sender sender = new Sender();
        Receiver receiver = new Receiver();
        Thread senderThread = new Thread(sender);
        Thread receiverThread = new Thread(receiver);
        PipedOutputStream out = sender.getPipedOutputStream(); // 写入
        PipedInputStream in = receiver.getPipedInputStream(); // 读出
        out.connect(in);// 将输出发送到输入
        senderThread.start();
        receiverThread.start();

    }
}
