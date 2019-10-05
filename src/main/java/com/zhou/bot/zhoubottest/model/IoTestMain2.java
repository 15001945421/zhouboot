package com.zhou.bot.zhoubottest.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName IoTestMain
 * @Description 描述这个类作用
 * @Author ZhouYouMing
 * @Date 2019/3/26 17:55
 */
public class IoTestMain2 {

    public void t1() throws Exception{

        List<String> list = new ArrayList<>();
        list.add("vipserver");list.add("vippage");list.add("gold");list.add("gmallpc");list.add("usermkt");
        list.add("myyhdweb");list.add("myservice");list.add("yuserweb");list.add("myyhdwxapi");list.add("tribackend");
        list.add("mytrialapi");list.add("bankcoiapi");list.add("bankbackd");list.add("yhdgrf");list.add("yhdcluster");
        list.add("ydhdist");list.add("clicktask");list.add("uploadfile");list.add("order");list.add("landing");
        list.add("yhdrtorder");list.add("rtorderdev");list.add("goldstg");list.add("vipwebpage");
        list.add("yuser");list.add("gmallv2");list.add("yhdusermkt");

        List<String> list_2 = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("d:\\我的文档\\桌面\\jsf升级&YHD\\依赖服务统计读取.txt")),
                "UTF-8"));
        String lineTxt = null;//<option value="1-m">1-m</option>
        while ((lineTxt = br.readLine()) != null) {
            int t1 = lineTxt.indexOf(">");
            int t2 = lineTxt.indexOf("</option>");
            String t = lineTxt.substring(t1+1,t2);
            for(String ss : list){
                String kk = "jdos_"+ss;
                if(t.equalsIgnoreCase(kk)){
                    System.out.println(ss);
                }
            }

        }
        br.close();


    }



    public static void main(String[] args) throws Exception{

        IoTestMain2 io = new IoTestMain2();
        io.t1();
    }
}
