package com.zhou.bot.zhoubottest.config;

import com.github.pagehelper.PageHelper;
import com.zhou.bot.zhoubottest.utils.BeanFactoryUtils;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName MybatisConfiguration
 * @Description mybatis配置，用于替代mybaits的xml配置
 * @Author ZhouYouMing
 * @Date 2019/1/15 16:44
 */
//@AutoConfigureAfter：表示自动注入在什么类加载前或者之后，即表示在DataSourceConfiguration加载完成后执行MybatisConfiguration加载
@Configuration
@AutoConfigureAfter(DataSourceConfiguration.class)
@MapperScan("com.zhou.bot.zhoubottest.mapper")
public class MybatisConfiguration  {

    private static Logger log = LoggerFactory.getLogger(MybatisConfiguration.class);

    //sqlxml文件地址
    @Value("${mybatis.mapper-locations}")
    private String mapperLocations;

    //全局的配置文件
    @Value("${mybatis.configLocation}")
    private String configLocation;

    //获取spring管理的主库数据源，这个注解很关键：@AutoConfigureAfter，因为masterDataSource这个bean是在DataSourceConfiguration中初始化的
    @Autowired
    @Qualifier("masterDataSource")
    private DataSource masterDataSource;

    //获取spring管理的主库数据源
    @Autowired
    @Qualifier("slaveDataSource")
    private DataSource slaveDataSource;

    //数据库数量
    @Value("${mysql.datasource.readSize}")
    private String dbDataSourceSize;

    //构造sqlSessionFactory，并设置数据源，类似于xml中的sqlSessionFactory配置
    @Bean(name="sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactorys() throws Exception {
        log.info("--------------------  sqlSessionFactory init ---------------------");
        try {
            SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
            //设置数据源
            sessionFactoryBean.setDataSource(roundRobinDataSouceProxy());

            //设置别名路径，sqlxml中的别名
            sessionFactoryBean.setTypeAliasesPackage("com.zhou.bot.zhoubottest.model");

            //设置mapper.xml文件所在位置
            Resource[] resources = new PathMatchingResourcePatternResolver().getResources(mapperLocations);
            sessionFactoryBean.setMapperLocations(resources);
            //设置mybatis-config.xml配置文件位置
            sessionFactoryBean.setConfigLocation(new DefaultResourceLoader().getResource(configLocation));

            //添加分页插件、打印sql插件
            Interceptor[] plugins = new Interceptor[]{pageHelper()};
            sessionFactoryBean.setPlugins(plugins);

            return sessionFactoryBean.getObject();
        } catch (IOException e) {
            log.error("mybatis resolver mapper*xml is error",e);
            return null;
        } catch (Exception e) {
            log.error("mybatis sqlSessionFactoryBean create error",e);
            return null;
        }
    }


    /**
     * 分页插件
     * @return
     */
    @Bean
    public PageHelper pageHelper() {
        PageHelper pageHelper = new PageHelper();
        Properties p = new Properties();
        p.setProperty("offsetAsPageNum", "true");
        p.setProperty("rowBoundsWithCount", "true");
        p.setProperty("reasonable", "true");
        p.setProperty("returnPageInfo", "check");
        p.setProperty("params", "count=countSql");
        pageHelper.setProperties(p);
        return pageHelper;
    }

    /**
     * 把所有数据库都放在路由中，获取数据源
     */
    @Bean(name="roundRobinDataSouceProxy")
    public AbstractRoutingDataSource roundRobinDataSouceProxy() {

        Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
        //把所有数据库都放在targetDataSources中,注意key值要和determineCurrentLookupKey()中代码写的一至，
        //否则切换数据源时找不到正确的数据源
        targetDataSources.put(DataSourceType.write.getType(), masterDataSource);
        //简易负载均衡使用，目前只设置了一个从库，因此这里设置为1，多个可以累加
        targetDataSources.put(DataSourceType.read.getType()+"1", slaveDataSource);

        final int readSize = Integer.parseInt(dbDataSourceSize);

        //路由类，寻找对应的数据源,采用匿名内部类实现抽象类AbstractRoutingDataSource
        AbstractRoutingDataSource proxy = new AbstractRoutingDataSource(){
            private AtomicInteger count = new AtomicInteger(0);
            /**
             * 这是AbstractRoutingDataSource类中的一个抽象方法，
             * 而它的返回值是你所要用的数据源dataSource的key值，有了这个key值，
             * targetDataSources就从中取出对应的DataSource，如果找不到，就用配置默认的数据源。
             */
            @Override
            protected Object determineCurrentLookupKey() {
                //获取设置的数据源标识值
                String typeKey = DataSourceContextHolder.getReadOrWrite();
                System.err.println("******determineCurrentLookupKey******:typeKey="+typeKey);
                if(typeKey == null){
                    throw new NullPointerException("数据库路由时，决定使用哪个数据库源类型不能为空...");
                }

                if (typeKey.equals(DataSourceType.write.getType())){
                    System.err.println("使用数据库write.............");
                    return DataSourceType.write.getType();
                }

                //读库， 简单负载均衡
                int number = count.getAndAdd(1);
                int lookupKey = number % readSize;
                System.err.println("使用数据库read-"+(lookupKey+1));
                return DataSourceType.read.getType()+(lookupKey+1);
            }
        };

        proxy.setDefaultTargetDataSource(masterDataSource);//默认库
        proxy.setTargetDataSources(targetDataSources);
        return proxy;
    }


    //数据访问模板，dao层会用到，参考链接：https://www.cnblogs.com/zemliu/p/3235721.html
    //关于依赖关系的bena注入，如：该方法的sqlSessionFactory参数，在初始化SqlSessionTemplate之前，会首先去初始化SqlSessionFactory
    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    //事务管理
    @Bean
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager((DataSource) BeanFactoryUtils.getBean("roundRobinDataSouceProxy"));
    }


}
