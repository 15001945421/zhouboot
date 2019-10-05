package com.zhou.bot.zhoubottest.utils;

import com.alibaba.fastjson.JSON;

import java.util.*;

/**
 * @ClassName Tg1
 * @Description 描述这个类作用
 * @Author ZhouYouMing
 * @Date 2019/9/26 9:27
 */
public class Tg1 {

    //根据组合系数，找出相邻连续组合中值相加中最小的索引位置，组合系数为x
    //对于数组：11,13,2,4,15,17 如果x = 2, 组合：11-13, 13-2, 2-4, 4-15, 15-17
    //找出11-13, 13-2, 2-4, 4-15, 15-17组合中，即值相加最小组合为 2-4，需要找出2-4所在数组中的位置，即2和3
    public static  void t(){
        List<Integer> list = Arrays.asList(11,13,12,14,5,1);
        int x = 2;//组合系数
        List<String> k1 = new ArrayList<>();//组合值，11-13, 13-2, 2-4, 4-15, 15-17
        List<String> k2 = new ArrayList<>();//索引值：0-1, 1-2, 2-3, 3-4, 4-5
        List<Integer> sum = new ArrayList<>();//相邻连续组合相加值：24, 15, 6, 19, 32
        Map<String,Integer> m1 = new HashMap<>();//key:索引值  value:相邻连续组合相加值, 即：0-1=24, 1-2=15, 2-3=6, 3-4=19, 4-5=32
        for(int i=0;i<list.size();i++){
            StringBuffer sb1 = new StringBuffer();
            StringBuffer sb2 = new StringBuffer();
            if(i<=list.size()-x){
                int u = 0;
                for(int j=i;j<i+x;j++){//循环条件为组合系数
                    u += list.get(j);//计算相邻连续组合相加值
                    sb1.append(list.get(j));//组合值
                    sb2.append(j);//索引值
                    if(j<(i+x)-1){
                        sb1.append("-");
                        sb2.append("-");
                    }
                }
                k1.add(sb1.toString());
                k2.add(sb2.toString());
                sum.add(u);
                m1.put(sb2.toString(),u);
            }

        }
        System.out.println(k1.toString());
        System.out.println(k2.toString());
        System.out.println(sum.toString());
        System.out.println(m1.toString());
        Collections.sort(sum);//相邻连续组合相加值排序操作
        String index = "";//得出索引位置
        for(Map.Entry<String,Integer> m : m1.entrySet()){
            if(m.getValue().intValue()==sum.get(0).intValue()){
                index = m.getKey();
                break;
            }
        }
        System.out.println(index);

    }

    public static void main(String[] args){
        //t();
        /*String str = "\n52422";
        if(str.contains("\n") || str.contains("\r\n")){
            String k = str.replaceAll("\r\n","");
            System.out.println(k);
        }*/
        //String kf = "1;2；3；4;5";
        //String[] km = kf.split("；|;");
        //System.out.println(JSON.toJSONString(km));

        Random random = new Random();
        //int randomNumber = random.nextInt(30)%(30-0+1) + 0;
        for(int i=0;i<100;i++){
            int randomNumber = random.nextInt(0);
            System.out.println(randomNumber);
        }
    }
}
