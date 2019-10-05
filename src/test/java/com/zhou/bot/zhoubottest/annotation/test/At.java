package com.zhou.bot.zhoubottest.annotation.test;

import java.lang.reflect.Method;
import java.util.UUID;

/**
 * @ClassName At
 * @Description 描述这个类作用
 * @Author ZhouYouMing
 * @Date 2019/7/25 13:57
 */
public class At {


    @PersonTest1(p="ppt")
    public String  a1(){
        return "a1";
    }
    public static void main(String[] args) throws Exception{

        Method[] ms = At.class.getDeclaredMethods();
        for(Method m : ms){
           PersonTest1 pi = m.getAnnotation(PersonTest1.class);
           if(pi!=null && pi.p().equals("ppt")){
               System.out.println(m.invoke(new At(),null));
           }
        }

    }
}
