package com.zhou.bot.zhoubottest.iotest;

import java.io.*;

/**
 * @ClassName MainObjectInAndOut
 * @Description 描述这个类作用
 * @Author ZhouYouMing
 * @Date 2019/3/26 20:53
 */
public class MainObjectInAndOut {

    //序列化
    public static void serializable(File file) throws Exception{
        OutputStream out = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(out);
        oos.writeObject(new PersonIo("Hello"));
        oos.close();
    }

    //反序列化
    public static void deserializable(File file) throws Exception{
        InputStream out = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(out);
        PersonIo personIo = (PersonIo)ois.readObject();
        ois.close();
        System.out.println(personIo);
    }

    public static void main(String[] args) throws Exception{
        File file = new File("d:/iotestwriter.txt");
        serializable(file);
        deserializable(file);
    }
}
