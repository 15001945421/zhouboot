package com.zhou.bot.zhoubottest.otest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.Field;
import java.util.HashMap;
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

    /*public Car(double price, String color)
    {
        this.price = price;
        this.color = color;
    }*/

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
        Car c1 = new Car();
        Map<String,Object> map = new HashMap<>();
        map.put("price",1);
        Car cv = JSON.parseObject("{\"price\":\"5\"}",Car.class);
        System.out.println(cv.getPrice());
        /*for(Field ff : car.getClass().getDeclaredFields()){
            if(map.containsKey(ff.getName())){
                System.out.println(map.get(ff.getName()));
            }
        }*/
        //System.out.println(JSON.toJSONString(car.getClass().getDeclaredFields()[2].getName()));

    }
}
