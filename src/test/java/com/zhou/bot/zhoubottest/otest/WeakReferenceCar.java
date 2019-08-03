package com.zhou.bot.zhoubottest.otest;

import com.alibaba.fastjson.JSON;

import java.lang.ref.WeakReference;

/**
 * @ClassName WeakReferenceCar
 * @Description 描述这个类作用
 * @Author ZhouYouMing
 * @Date 2019/4/24 20:44
 */
public class WeakReferenceCar extends WeakReference<Car> {


    /**
     * Creates a new weak reference that refers to the given object.  The new
     * reference is not registered with any queue.
     *
     * @param referent object the new weak reference will refer to
     */
    public WeakReferenceCar(Car referent) {
        super(referent);
    }

    public static void main(String[] args){
        //Car car = new Car(2000.0, "red");
        //WeakReferenceCar wrc = new WeakReferenceCar(car);
        int i = 0;
        //System.out.println(JSON.toJSONString(wrc.get()));
        /*while (true)
        {
            if (wrc.get() != null)
            {
                i++;
                System.out.println("WeakReferenceCar's Car is alive for " + i + ", loop - " + wrc);
            }
            else
            {
                System.out.println("WeakReferenceCar's Car has bean collected");
                break;
            }
        }*/
    }
}
