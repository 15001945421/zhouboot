package com.zhou.bot.zhoubottest.threadtest.futuretest;

import java.util.concurrent.Callable;

/**
 * @ClassName FutureR1
 * @Description 描述这个类作用
 * @Author ZhouYouMing
 * @Date 2019/8/9 19:46
 */
public class FutureR2 implements Callable<String> {

    @Override
    public String call() throws Exception {
        return "FutureR2";
    }
}
