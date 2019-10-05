package com.zhou.bot.zhoubottest.java8.t1;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName Java8T1
 * @Description 描述这个类作用
 * @Author ZhouYouMing
 * @Date 2019/8/27 16:26
 */
public class Java8T1 {

    public static void t1(){
        U8_1 u1 = new U8_1();
        u1.setId(1);u1.setName("b");

        U8_1 u2 = new U8_1();
        u2.setId(4);u2.setName("a");

        U8_1 u3 = new U8_1();
        u3.setId(3);u3.setName("a");

        List<U8_1> list = new ArrayList<>();
        list.add(u1);list.add(u2);list.add(u3);

        List<U8_1> newlist = null;
        //newlist = list.stream().filter((U8_1 u8)->u8.getId()>2).collect(Collectors.toList());
        newlist = list.stream().sorted(Comparator.comparing(U8_1::getId).reversed()).collect(Collectors.toList());
        System.out.println(JSON.toJSONString(newlist));
    }


    public static class U8_1 {
        private String name;
        private Integer id;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }
    }

    public static void main(String[] args){
        t1();
    }
}
