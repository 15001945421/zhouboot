package com.zhou.bot.zhoubottest.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhou.bot.zhoubottest.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @ClassName RedisUtils
 * @Description 描述这个类作用
 * @Author ZhouYouMing
 * @Date 2019/2/16 22:58
 */
@Component
public class RedisUtils {
    //@Autowired
    //private StringRedisTemplate template;

    /**redisTemplate定义了5中数据结构操作类型
     redisTemplate.opsForValue();//操作字符串
     redisTemplate.opsForHash();//操作hash
     redisTemplate.opsForList();//操作list
     redisTemplate.opsForSet();//操作set
     redisTemplate.opsForZSet();//操作有序set
     */
    @Autowired
    private RedisTemplate redisTemplate;

    /*private static ObjectMapper objectMapper = new ObjectMapper();
    private String convertObj2String(Object object) {
        String s = null;
        try {
            s = objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    private static <T> T convertString2Obj(String s, Class<T> clazz) {
        T t = null;
        try {
            t = objectMapper.readValue(s, clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return t;
    }*/

    /**
     * 默认过期时长，单位：秒
     */
    public static final long DEFAULT_EXPIRE = 60 * 60 * 24;


    /**
     * 将 key，value 存放到redis数据库中，设置过期时间单位是秒
     * @param key
     * @param value
     * @param expireTime
     */
    public void setKey(String key, Object value, long expireTime) {
        try {
            if(expireTime<=0){
                ValueOperations<String, Object> ops = this.redisTemplate.opsForValue();
                ops.set(key, value, DEFAULT_EXPIRE, TimeUnit.SECONDS);
            }else{
                ValueOperations<String, Object> ops = this.redisTemplate.opsForValue();
                ops.set(key, value, expireTime, TimeUnit.SECONDS);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *@Description 判断 key 是否在 redis 数据库中
     *@Param [key]
     *@Return boolean
     *@Author ZhouYouMing
     *@Date 2019/2/19
     *@Time 13:43
     */
    public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }


    public <T> T getKey(String key) {
        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
        if (ops == null) {
            return null;
        }
        return (T)ops.get(key);
    }

    /*public <T> Result<T> getKey2(String key) {
        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
        if (ops == null) {
            return null;
        }
        return new Result<T>().setData(ops.get(key));
    }*/

    //***************list操作********************/
    public void leftPush(String key,List<? extends Object> list){
        ListOperations<String, Object> vo = redisTemplate.opsForList();
        vo.leftPush(key,list);
    }

    public <T> T leftPop(String key){
        ListOperations<String, Object> vo = redisTemplate.opsForList();
        Object o = vo.leftPop(key);
        return (T)o;
    }

}
