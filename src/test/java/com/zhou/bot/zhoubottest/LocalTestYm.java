package com.zhou.bot.zhoubottest;

import com.zhou.bot.zhoubottest.config.DataSourceType;

/**
 * @ClassName LocalTestYm
 * @Description 描述这个类作用
 * @Author ZhouYouMing
 * @Date 2019/1/18 14:02
 */
public class LocalTestYm {

    private static final ThreadLocal<String> local = new ThreadLocal<String>();

    public static void setRead() {
        local.set(DataSourceType.read.getType());
    }

    /**
     * 写库
     */
    public static void setWrite() {
        local.set(DataSourceType.write.getType());
    }

    //获取当前线程设置的数据源
    public static String getReadOrWrite() {
        return local.get();
    }

    public static void main(String[] args){

        System.out.println(LocalTestYm.getReadOrWrite());

        TestAbs testAbs = new TestAbs() {
            @Override
            public String abs() {
                return "abcd";
            }
        };
        System.out.println(testAbs.abs());
    }
}
