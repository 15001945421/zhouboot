package com.zhou.bot.zhoubottest.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Component;

/**
 * 获取bean的工具类
 */
@Component
public class BeanFactoryUtils implements BeanFactoryAware {

    private static BeanFactory factory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        setBean(beanFactory);
    }

    public static Object getBean(String beanName) {
        if (factory == null) {
            throw new NullPointerException("BeanFactory is null!");
        }
        return factory.getBean(beanName);
    }

    private static void setBean(BeanFactory beanFactory) {
        factory = beanFactory;
    }


}
