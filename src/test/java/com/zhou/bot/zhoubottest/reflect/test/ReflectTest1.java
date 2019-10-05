package com.zhou.bot.zhoubottest.reflect.test;

import java.lang.reflect.Method;

/**
 * @ClassName ReflectTest1
 * @Description 描述这个类作用
 * @Author ZhouYouMing
 * @Date 2019/9/18 20:23
 */
public class ReflectTest1 {


    public static void main(String[] args){
        try {
            Reflect_A s = new Reflect_A();
            Class<?> clz = Class.forName("com.zhou.bot.zhoubottest.reflect.test.Reflect_A");
            Object o = clz.newInstance();
            Method m = clz.getDeclaredMethod("rt",null);
            String rs = (String) m.invoke(o);
            System.out.println(rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
