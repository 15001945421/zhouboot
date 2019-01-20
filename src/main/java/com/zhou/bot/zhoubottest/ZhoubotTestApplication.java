package com.zhou.bot.zhoubottest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.DispatcherServlet;

/**
 *@Description 启动类，自动扫描com.zhou.bot.zhoubottest下的注解
 *@Author ZhouYouMing
 *@Date 2019/1/17
 *@Time 19:52
 */
@SpringBootApplication
@ComponentScan("com.zhou.bot.zhoubottest")
public class ZhoubotTestApplication {

    /**
     *@Description 作用：定义匹配springMVC的请求路径，
     *类似于web.xml中配置的mvc请求路径匹配规则,这里只匹配以.html结尾的请求
     *示例：http://localhost:8080/index/4.html
     *@Param [dispatcherServlet]
     *@Return org.springframework.boot.web.servlet.ServletRegistrationBean
     *@Author ZhouYouMing
     *@Date 2019/1/17
     *@Time 19:53
     */
    //@Bean:类似于xml文件中<bean id='xxx' class='xxx'>...</bean>
    @Bean
    public ServletRegistrationBean servletRegistrationBean(DispatcherServlet dispatcherServlet) {
        ServletRegistrationBean<DispatcherServlet> servletServletRegistrationBean = new ServletRegistrationBean<>(dispatcherServlet);
        servletServletRegistrationBean.addUrlMappings("*.html");
        return servletServletRegistrationBean;
    }

    public static void main(String[] args) {
        SpringApplication.run(ZhoubotTestApplication.class, args);
    }

}

