package com.zhou.bot.zhoubottest.otest;

import java.io.*;

/**
 * @ClassName StaticTerst
 * @Description 描述这个类作用
 * @Author ZhouYouMing
 * @Date 2019/4/18 20:12
 */
public class StaticTerst2 implements Serializable {
    private static final long serialVersionUID = -7912127938757805628L;
    private static  String s = 1+"";
    private   int t;
    private transient Integer  k;

    public  StaticTerst2(int t,Integer k){
        this.t = t;
        this.k = k;
    }

    public Integer getK() {
        return k;
    }

    public void setK(Integer k) {
        this.k = k;
    }

    public static String getS() {
        return s;
    }

    public int getT() {
        return t;
    }

    public void setT(int t) {
        this.t = t;
    }

    public static void main(String[] args) throws Exception{
         File file = new File("D:" + File.separator + "stacccccc.txt");
         OutputStream os = new FileOutputStream(file);
         ObjectOutputStream oos = new ObjectOutputStream(os);
         oos.writeObject(new StaticTerst2(1,200));
         oos.close();

         InputStream is = new FileInputStream(file);
         ObjectInputStream ois = new ObjectInputStream(is);
        StaticTerst2 so = (StaticTerst2)ois.readObject();
         System.out.println("so t = " + so.getT());
        System.out.println("so k = " + so.getK());
        System.out.println("so s = " + so.getS());
         ois.close();
    }
}
