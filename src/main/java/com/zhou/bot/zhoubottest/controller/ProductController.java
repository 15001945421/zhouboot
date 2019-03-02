package com.zhou.bot.zhoubottest.controller;

import com.alibaba.fastjson.JSON;
import com.zhou.bot.zhoubottest.mapper.ProductMapper;
import com.zhou.bot.zhoubottest.model.User;
import com.zhou.bot.zhoubottest.service.ProductService;
import com.zhou.bot.zhoubottest.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private RedisUtils redisUtils;

    @RequestMapping("/index/{id}")
    public String master(@PathVariable("id") Long id ) {
        try {
            System.out.println(productService.masterProductInfo(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Hello World";
    }

    @RequestMapping("/slave/{id}")
    public String slave(@PathVariable("id") Long id ) {
        System.out.println(productService.slaveProductInfo(id));
        return "Hello World";
    }

    @RequestMapping("/redis/{key}/{value}")
    public String redis(@PathVariable("key") String key, @PathVariable("value") String value) {
        redisUtils.setKey(key,value,1L);
        System.out.println((String)redisUtils.getKey(key));
        return "redis";
    }

    @RequestMapping("/redisList/{key1}/{key2}")
    public String redisList(@PathVariable("key1") String key1, @PathVariable("key2") String key2 ) {
        User user = new User();
        user.setUserName("zhangsanfeng");
        redisUtils.setKey(key1,user,1L);
        User cacheU = redisUtils.getKey(key1);
        List<User> list = new ArrayList<User>();
        list.add(user);
        redisUtils.leftPush(key2,list);
        List<User> cacheList = redisUtils.leftPop(key2);
        System.out.println(JSON.toJSONString(redisUtils.leftPop(key2)));
        String ss = "key1:"+key1+" value值："+ JSON.toJSONString(cacheU)+"  key2:"+key2+" 获取的值为："+JSON.toJSONString(cacheList);
        return ss;
    }

    @RequestMapping("/redisHashByPut/{key}/{v1}/{v2}")
    public String redisHashByPut(@PathVariable("key") String key, @PathVariable("v1") String v1,@PathVariable("v1") String v2 ) {

        StringBuffer sb = new StringBuffer();

        redisUtils.redisHashByPut(key,v1,v2);
        sb.append("key:").append(key).append(" 缓存值：").append(JSON.toJSONString(redisUtils.redisHashByGet(key,v1)));

        User user = new User();
        user.setUserName("tudouaa");
        redisUtils.redisHashByPut("tudou","tuser",user);
        sb.append("\n").append("\n").append("key:").append("tudou").append(" tuser值为：").append(JSON.toJSONString(redisUtils.redisHashByGet("tudou","tuser")));

        return sb.toString();
    }


    @RequestMapping("/redisHashByPutAll/{key}/{ky}")
    public String redisHashByPutAll(@PathVariable("key") String key) {

        StringBuffer sb = new StringBuffer();

        Map<String,User> map = new HashMap<String,User>();
        User user = new User();
        user.setUserName("tudoucc");
        map.put("tudd",user);
        redisUtils.redisHashByPutAll(key,map);
        sb.append("key:").append(key).append(" map值为：").append(JSON.toJSONString(redisUtils.redisHashByGet(key,"tuser")));

        return sb.toString();
    }
}
