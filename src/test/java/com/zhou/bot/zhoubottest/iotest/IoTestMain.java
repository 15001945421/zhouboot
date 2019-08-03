package com.zhou.bot.zhoubottest.iotest;

import java.io.*;

/**
 * @ClassName IoTestMain
 * @Description 描述这个类作用
 * @Author ZhouYouMing
 * @Date 2019/3/26 17:55
 */
public class IoTestMain {

    public void t1() throws Exception{

        InputStreamReader isr = new InputStreamReader(System.in);
        FileOutputStream fos = new FileOutputStream("d:/iotestwriter.txt");
        OutputStreamWriter osw = new OutputStreamWriter(fos);
        char[] chars = new char[1024];
        int n = 0;
        First:while ((n=isr.read(chars))!=-1){
            for(int k=0;k < n;k++){
                System.out.println("chars:"+chars[k]);
                if(chars[k] == 's'){
                    break First;
                }
                osw.write(chars[k]);
            }
        }
        isr.close();
        osw.close();
        fos.close();
    }

    public void t2() throws Exception{
        File file = new File("D:/buffered.txt");
        Writer writer = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(writer);
    }

    public static void main(String[] args) throws Exception{

        IoTestMain io = new IoTestMain();
        io.t1();
    }
}
