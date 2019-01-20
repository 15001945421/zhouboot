package com.zhou.bot.zhoubottest.aop;

import com.zhou.bot.zhoubottest.config.DataSourceContextHolder;
import com.zhou.bot.zhoubottest.config.DataSourceType;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;

/**
 * @ClassName DataSourceAopInService
 * @Description AOP拦截：在service层拦截数据源，不是在DAO层拦截数据源
 * @Author ZhouYouMing
 * @Date 2019/1/16 18:35
 */
//EnableAspectJAutoProxy开启Aop
@Aspect
@EnableAspectJAutoProxy(exposeProxy=true,proxyTargetClass=true)
@Component
public class DataSourceAopInService implements PriorityOrdered {


    private static Logger log = LoggerFactory.getLogger(DataSourceAopInService.class);

    @Before("execution(* com.zhou.bot.zhoubottest.service..*.*(..)) "
            + " && @annotation(com.zhou.bot.zhoubottest.config.ReadDataSource) ")
    public void setReadDataSourceType() {
        //如果已经开启写事务了，那之后的所有读都从写库读，
        //为何这么做？原因在于如果一个事务读取时是备库，而更新却是主库，
        //这个事务会失效，一个事务里面不能做数据源切换
        if(!DataSourceType.write.getType().equals(DataSourceContextHolder.getReadOrWrite())){
            DataSourceContextHolder.setRead();
        }

    }

    @Before("execution(* com.zhou.bot.zhoubottest.service..*.*(..)) "
            + " && @annotation(com.zhou.bot.zhoubottest.config.WriteDataSource) ")
    public void setWriteDataSourceType() {
        DataSourceContextHolder.setWrite();
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
