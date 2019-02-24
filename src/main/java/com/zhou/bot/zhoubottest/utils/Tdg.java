package com.zhou.bot.zhoubottest.utils;

import com.zhou.bot.zhoubottest.model.User;

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
    public static void main(String[] args){

        t1(new User());
    }
}
