package com.zhou.bot.zhoubottest.controller;

import com.alibaba.fastjson.JSON;
import com.zhou.bot.zhoubottest.model.User;
import com.zhou.bot.zhoubottest.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 *@Description redis操作控制层
 *@Author ZhouYouMing
 *@Date 2019/3/2
 *@Time 15:07
 */
@RestController
public class RedisOptController {


    @Autowired
    private RedisUtils redisUtils;


    /**
     *@Description 操作普通字符串值：set key 以及get key
     *@Param [key, value]
     *@Return java.lang.String
     *@Author ZhouYouMing
     *@Date 2019/3/2
     *@Time 14:53
     */
    @RequestMapping("/redis/{key}/{value}")
    public String redis(@PathVariable("key") String key, @PathVariable("value") String value) {
        redisUtils.setKey(key,value,1L);
        return (String)redisUtils.getKey(key);
    }

    /**
     *@Description 操作对象类型：set key 以及get key
     *@Param [key]
     *@Return java.lang.String
     *@Author ZhouYouMing
     *@Date 2019/3/2
     *@Time 14:58
     */
    @RequestMapping("/redisObj/{key}")
    public String redisObj(@PathVariable("key") String key) {
        User user = new User();
        user.setUserName("zhangsanfeng");
        redisUtils.setKey(key,user,1L);
        return JSON.toJSONString(redisUtils.getKey(key));
    }

    /**
     *@Description 操作list类型:push
     *@Param [key1, key2]
     *@Return java.lang.String
     *@Author ZhouYouMing
     *@Date 2019/3/2
     *@Time 14:54
     */
    @RequestMapping("/redisList/{key}")
    public String redisList(@PathVariable("key") String key) {
        User user = new User();
        user.setUserName("zhangsanfeng");
        List<User> list = new ArrayList<User>();
        list.add(user);
        redisUtils.leftPush(key,list);
        return "success";
    }

    /**
     *@Description 操作list类型:pop
     *@Param [key1, key2]
     *@Return java.lang.String
     *@Author ZhouYouMing
     *@Date 2019/3/2
     *@Time 14:54
     */
    @RequestMapping("/redisListPop/{key}")
    public String redisListPop(@PathVariable("key") String key) {
        List<User> cacheList = redisUtils.leftPop(key);
        String ss = "key:"+key+" 获取的值为："+JSON.toJSONString(cacheList);
        return ss;
    }

    /**
     *@Description 操作hash类型：put & get
     *@Param [key, v1, v2]
     *@Return java.lang.String
     *@Author ZhouYouMing
     *@Date 2019/3/2
     *@Time 14:58
     */
    @RequestMapping("/redisHashByPut/{key}/{key2}/{v1}/{v2}")
    public String redisHashByPut(@PathVariable("key") String key,
                                 @PathVariable("key2") String key2,
                                 @PathVariable("v1") String v1,
                                 @PathVariable("v2") String v2 ) {

        StringBuffer sb = new StringBuffer();

        redisUtils.redisHashByPut(key,v1,v2);
        sb.append("key:").append(key).append(" 缓存值：").append(JSON.toJSONString(redisUtils.redisHashByGet(key,v1)));

        User user = new User();
        user.setUserName("tudouaa");
        redisUtils.redisHashByPut(key2,"tuser",user);
        sb.append("\n").append("\n").append("key2:").append(key2).append(" user值为：").append(JSON.toJSONString(redisUtils.redisHashByGet(key2,"tuser")));

        return sb.toString();
    }


    /**
     *@Description 操作hash类型：putAll & get
     *@Param [key, mu]
     *@Return java.lang.String
     *@Author ZhouYouMing
     *@Date 2019/3/2
     *@Time 15:01
     */
    @RequestMapping("/redisHashByPutAll/{key}/{mu}")
    public String redisHashByPutAll(@PathVariable("key") String key,@PathVariable("mu") String mu) {

        StringBuffer sb = new StringBuffer();

        Map<String,User> map = new HashMap<String,User>();
        User user = new User();
        user.setUserName("tudoucc");
        map.put(mu,user);
        redisUtils.redisHashByPutAll(key,map);
        sb.append("key:").append(key).append(" map值为：").append(JSON.toJSONString(redisUtils.redisHashByGet(key,mu)));

        return sb.toString();
    }

    /**
     *@Description 操作hash类型：entries,获取hash集合全部值
     *@Param [key]
     *@Return java.lang.String
     *@Author ZhouYouMing
     *@Date 2019/3/2
     *@Time 15:02
     */
    @RequestMapping("/redisHashByEntries/{key}")
    public String redisHashByEntries(@PathVariable("key") String key){
        Map<Object,Object> map = redisUtils.redisHashByEntries(key);
        String res = JSON.toJSONString(map);
        return res;
    }

    /**
     *@Description 操作set类型：add & members（获取集合所有值）
     *@Param [key, v1, v2, v3, v4, check]
     *@Return java.lang.String
     *@Author ZhouYouMing
     *@Date 2019/3/2
     *@Time 15:02
     */
    @RequestMapping("/redisSetByAdd/{key}/{v1}/{v2}/{v3}/{v4}/{check}")
    public String redisSetByAdd(@PathVariable("key") String key,
                                @PathVariable("v1") String v1,
                                @PathVariable("v2") String v2,
                                @PathVariable("v3") String v3,
                                @PathVariable("v4") String v4,
                                @PathVariable("check") String check){
        redisUtils.redisSetByAdd(key,v1,v2,v3,v4);
        boolean ise = redisUtils.redisSetByIsMember(key,check);
        StringBuffer sb = new StringBuffer();
        sb.append("key:").append(key).append(" 是否存在：").append(ise);
        Set<Object> sets = redisUtils.redisSetBymembers(key);
        sb.append(" 该集合所有元素为：").append(JSON.toJSONString(sets));
        return sb.toString();
    }

    /**
     *@Description 操作Zet类型：zadd
     *@Param [key, v1, v2]
     *@Return java.lang.String
     *@Author ZhouYouMing
     *@Date 2019/3/2
     *@Time 15:03
     */
    @RequestMapping("/redisZSetByZAdd/{key}/{v1}/{v2}")
    public String redisZSetByZAdd(@PathVariable("key") String key,
            @PathVariable("v1") String v1,
            @PathVariable("v2") String v2){
        redisUtils.redisZSetByZAdd(key,v1,Double.valueOf(v2));
       return "success";
    }

    /**
     *@Description 操作Zet类型：ZRange 获取指定范围内的集合值(非分值)
     *@Param [key, v1, v2]
     *@Return java.lang.String
     *@Author ZhouYouMing
     *@Date 2019/3/2
     *@Time 15:04
     */
    @RequestMapping("/redisZSetByZRange/{key}/{v1}/{v2}")
    public String redisZSetByZRange(@PathVariable("key") String key,
                                  @PathVariable("v1") String v1,
                                  @PathVariable("v2") String v2){
        Set<Object> sets = redisUtils.redisZSetByZRange(key,Long.valueOf(v1),Long.valueOf(v2));
        return "key:"+key+" 集合值为："+JSON.toJSONString(sets);
    }

    /**
     *@Description 操作Zet类型：RangeWithScores 获取指定范围内的集合值(包含分值)
     *@Param [key, v1, v2]
     *@Return java.lang.String
     *@Author ZhouYouMing
     *@Date 2019/3/2
     *@Time 15:04
     */
    @RequestMapping("/redisZSetByRangeWithScores/{key}/{v1}/{v2}")
    public String redisZSetByRangeWithScores(@PathVariable("key") String key,
                                    @PathVariable("v1") String v1,
                                    @PathVariable("v2") String v2){
        Map<Object,Double> maps = redisUtils.redisZSetByRangeWithScores(key,Long.valueOf(v1),Long.valueOf(v2));
        return "key:"+key+" 集合值为："+JSON.toJSONString(maps);
    }
}
