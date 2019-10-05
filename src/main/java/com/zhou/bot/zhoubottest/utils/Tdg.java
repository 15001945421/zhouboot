package com.zhou.bot.zhoubottest.utils;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.zhou.bot.zhoubottest.model.User;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName Tdg
 * @Description 描述这个类作用
 * @Author ZhouYouMing
 * @Date 2019/2/20 13:47
 */
public class Tdg {
    public static void t1 (Object o){
        System.out.println(o.getClass().getName());
    }

    public static  void t2(){
        //增加对会场sku的校验
        String activitySecondSkus = "12 3,45 6,72523,124";
        if(StringUtils.isBlank(activitySecondSkus)){
            System.out.println("小会场sku为空");
            return;
        }
        if(activitySecondSkus.contains(",") && activitySecondSkus.contains("，")){
            System.out.println("小会场sku分割符不正确,请全部使用英文分割符");
            return;
        }
        activitySecondSkus = activitySecondSkus.replace(" ","");
        if(activitySecondSkus.startsWith(",")){
            activitySecondSkus = activitySecondSkus.substring(1,activitySecondSkus.length());
        }
        if(activitySecondSkus.endsWith(",")){
            activitySecondSkus = activitySecondSkus.substring(0,activitySecondSkus.length()-1);
        }
        String[] activitySecondSkusArray = activitySecondSkus.split(",");
        if(activitySecondSkusArray.length<3){
            System.out.println("小会场sku数量小于3个");
            return;
        }
        System.out.println("最终值："+activitySecondSkus);
    }
    public static void main(String[] args){

        //t1(new User());
        /*String ss = ",1234  ,";
        if(ss.startsWith(",")){
            System.out.println("startsWith");
            ss = ss.substring(1,ss.length());
        }

        if(ss.endsWith(",")){
            System.out.println("endsWith");
            ss = ss.substring(0,ss.length()-1);
        }

        System.out.println("hkil: "+ss);

        String[] k = ss.split(",");
        StringBuffer sb = new StringBuffer();*/
        //for(String  m : k){
            /*if(ss.contains(" ")){
                ss = ss.replace(" ","");
                System.out.println(ss);
            }*/
            //if(ss.contains(",") ){
            //    ss = ss.replace(",","");
            //}
            //sb.append(m);
        //}

        /*if(ss.contains("，") ){
            ss = ss.replace("，","");
        }*/

       // System.out.println(ss);


        /*String t = "12,123,234,23";
        if(t.contains(",") && t.contains("，")){
            System.out.println(true);
        }
        System.out.println("严".getBytes().length);*/

        //t2();

        //String ss = "";
        //ss.replace(" ","");
        //System.out.println(ss);

        Map<String,Object> m = new HashMap<>();
        m.put("open",false);m.put("range",19);
        System.out.println(JSON.toJSONString(m));

        String s ="{\"range\":19,\"open\":false}";
        Map<String,Object> m2 = (Map<String, Object>) JSONUtils.parse(s);
        System.out.println(m2.toString());

    }
}
