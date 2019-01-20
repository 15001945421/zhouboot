package com.zhou.bot.zhoubottest.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @ClassName DataSourceConfiguration
 * @Description 数据源配置
 * @Author ZhouYouMing
 * @Date 2019/1/15 16:11
 */
@Configuration//用于定义配置类，替代xml文件，被注解的类内部包含有一个或多个被@Bean注解的方法，这些方法将会被AnnotationConfigApplicationContext或AnnotationConfigWebApplicationContext类进行扫描，并用于构建bean定义，初始化Spring容器。
@EnableTransactionManagement //开启事物spring提供的注解
public class DataSourceConfiguration {


    private static Logger log = LoggerFactory.getLogger(DataSourceConfiguration.class);

    //默认去找application.yml，将配置文件中的该值赋值给dataSourceType
    @Value("${mysql.datasource.type}")
    private Class<? extends DataSource> dataSourceType;

    //@Bean的作用类似于xml中的<bean>....</bean>
    @Bean(name = "masterDataSource")
    @Primary//优先选择主数据源（原因可写可读）
    @ConfigurationProperties(prefix = "mysql.datasource.master") //意思是从application.yml中找dmysql.datasource.master开头所有的信息都要放到要创建的masterDataSource并且交给spring管理
    public DataSource masterDataSource() throws SQLException {
        DataSource masterDataSource = DataSourceBuilder.create().type(dataSourceType).build();
        return masterDataSource;
    }

    //设置备库数据源，并交由spring管理
    @Bean(name = "slaveDataSource")
    @ConfigurationProperties(prefix = "mysql.datasource.slave")
    public DataSource slaveDataSource(){
        DataSource slaveDataSource = DataSourceBuilder.create().type(dataSourceType).build();
        return slaveDataSource;
    }



}
