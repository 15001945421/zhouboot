package com.zhou.bot.zhoubottest.service;

import com.zhou.bot.zhoubottest.config.ReadDataSource;
import com.zhou.bot.zhoubottest.config.WriteDataSource;
import com.zhou.bot.zhoubottest.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName ProductService
 * @Description 描述这个类作用
 * @Author ZhouYouMing
 * @Date 2019/1/16 18:44
 */
@Service
public class ProductService {

    @Autowired
    private ProductMapper productMapper;

    @WriteDataSource
    @Transactional(propagation= Propagation.REQUIRED,isolation= Isolation.DEFAULT,rollbackFor=Exception.class,readOnly=false)
    public  String masterProductInfo (Long id) throws Exception{
        Integer uc = null;
        try {
            uc = productMapper.updateProName(id);
            System.out.println("spring-boot读写分离测试事物回滚："+uc);
            //int s = 5/0;

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        return uc+"";
    }

    @ReadDataSource
    public  String slaveProductInfo(Long id){
        String ss = productMapper.getProName(id);
        System.out.println(ss);
        return  ss;
    }
}
