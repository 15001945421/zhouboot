package com.zhou.bot.zhoubottest.threadtest.futuretest;

import java.util.concurrent.Callable;

/**
 * @ClassName FutureR1
 * @Description 描述这个类作用
 * @Author ZhouYouMing
 * @Date 2019/8/9 19:46
 */
public class FutureR1 implements Callable<String> {

    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public String call() throws Exception {
        return "FutureR1";
    }
}
