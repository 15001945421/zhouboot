package com.zhou.bot.zhoubottest.otest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName Car
 * @Description 描述这个类作用
 * @Author ZhouYouMing
 * @Date 2019/4/24 20:41
 */
public class Car {

    private double     price;
    private String    color;

    public Car(double price, String color)
    {
        this.price = price;
        this.color = color;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String toString()
    {
        return "This car is a " + this.color + " car, costs $" + price;
    }


    public static void main(String[] args){
        List<Integer> list = new ArrayList<>();
        list.add(11);list.add(22);list.add(33);list.add(44);
        Integer id = list.get(2);
        list.remove(2);
        list.add(0,id);
        System.out.println(JSON.toJSONString(list));
    }
}
