package com.zhou.bot.zhoubottest.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName MyWebMvcConfigurer
 * @Description 请求后缀匹配,参考链接：https://blog.csdn.net/qq_39403545/article/details/83245589
 * @Author ZhouYouMing
 * @Date 2019/1/14 17:48
 */

@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        //开启请求后缀匹配
        configurer.setUseRegisteredSuffixPatternMatch(true);
    }
}
