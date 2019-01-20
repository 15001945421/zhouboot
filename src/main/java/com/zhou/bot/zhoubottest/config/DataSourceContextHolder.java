package com.zhou.bot.zhoubottest.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName DataSourceContextHolder
 * @Description 本地线程，数据源上下文，将自己的数据源放置到本地线程变量里面，可以跨方法获取数据源
 * @Author ZhouYouMing
 * @Date 2019/1/15 18:10
 */
public class DataSourceContextHolder {

    private static Logger log = LoggerFactory.getLogger(DataSourceContextHolder.class);

    //线程本地环境
    private static final ThreadLocal<String> local = new ThreadLocal<String>();

    public static ThreadLocal<String> getLocal() {
        return local;
    }

    /**
     * 读库
     */
    public static void setRead() {
        local.set(DataSourceType.read.getType());
        log.info("数据库切换到读库...");
    }

    /**
     * 写库
     */
    public static void setWrite() {
        local.set(DataSourceType.write.getType());
        log.info("数据库切换到写库...");
    }

    //获取当前线程设置的数据源
    public static String getReadOrWrite() {
        return local.get();
    }

    //清除数据源
    public static void clear(){
        local.remove();
    }

}
