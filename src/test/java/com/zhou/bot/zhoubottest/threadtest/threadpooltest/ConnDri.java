package com.zhou.bot.zhoubottest.threadtest.threadpooltest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName ConnDri
 * @Description 动态代理实现获取java.sql的connection对象
 * @Author ZhouYouMing
 * @Date 2019/8/11 9:40
 */
public class ConnDri {

    static class ConnectionHandler implements InvocationHandler {


        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if(method.getName().equals("commit")){
                TimeUnit.MILLISECONDS.sleep(100);
            }
            return null;
        }

    }

    public static final Connection create(){
        return (Connection) Proxy.newProxyInstance(ConnDri.class.getClassLoader(),new Class<?>[]{Connection.class},new ConnectionHandler());
    }
}
