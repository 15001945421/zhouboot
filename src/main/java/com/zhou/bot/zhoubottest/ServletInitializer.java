package com.zhou.bot.zhoubottest;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;

/**
 *@Description 可以看做是Web.xml的替代,打war包，用外部容器启动，则需要继承至该类：SpringBootServletInitializer
 * 参考链接：https://blog.csdn.net/jia970426/article/details/79759566
 *@Author ZhouYouMing
 *@Date 2019/1/17
 *@Time 20:05
 */
@Configuration
@EnableAutoConfiguration
public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ZhoubotTestApplication.class);
    }

}
