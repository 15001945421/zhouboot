package com.zhou.bot.zhoubottest.model;

import java.io.Serializable;

/**
 * @ClassName Result
 * @Description 描述这个类作用
 * @Author ZhouYouMing
 * @Date 2019/2/21 13:52
 */
public class Result<T extends Object> implements Serializable {

    public T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


}
