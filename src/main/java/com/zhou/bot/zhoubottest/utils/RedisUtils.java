package com.zhou.bot.zhoubottest.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhou.bot.zhoubottest.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;
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

    //参考链接：https://blog.csdn.net/fengyao1995/article/details/72794899
    //https://357029540.iteye.com/blog/2388965、https://www.linuxidc.com/Linux/2018-09/154525.htm
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


    /**
     *@Description 获取对应的key值，针对ValueOperations类型的操作
     *@Param [key]
     *@Return T
     *@Author ZhouYouMing
     *@Date 2019/2/27
     *@Time 13:21
     */
    public <T> T getKey(String key) {
        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
        if (ops == null) {
            return null;
        }
        return (T)ops.get(key);
    }

    //***************list操作********************/
    /**
     *@Description 向list中添加元素
     *@Param [key, list]
     *@Return void
     *@Author ZhouYouMing
     *@Date 2019/2/27
     *@Time 13:21
     */
    public void leftPush(String key,List<? extends  Object> list){
        ListOperations<String, Object> vo = redisTemplate.opsForList();
        vo.leftPush(key,list);
    }

    public <T> T leftPop(String key){
        ListOperations<String, Object> vo = redisTemplate.opsForList();
        return (T)vo.leftPop(key);
    }
    //***************list操作********************/

    //***************hash操作********************/
    public void redisHashByPutAll(String key, Map<? extends Object,? extends Object> valueMap){
        HashOperations<String, Object, Object> hashOperations = redisTemplate.opsForHash();
        hashOperations.putAll(key,valueMap);
    }

    public void redisHashByPut(String key, Object o1,Object o2){
        HashOperations<String, Object, Object> hashOperations = redisTemplate.opsForHash();
        hashOperations.put(key,o1,o2);
    }

    /**
     *@Description redisHashByGet
     *@Param [key-表示所有域对应的键值, o1-域对应的键值，相当于hashmap中的key]
     *@Return T
     *@Author ZhouYouMing
     *@Date 2019/2/27
     *@Time 13:16
     */
    public <T> T redisHashByGet(String key, Object o1){
        HashOperations<String, Object, Object> hashOperations = redisTemplate.opsForHash();
        return (T)hashOperations.get(key,o1);
    }

    /**
     *@Description 获取该key下面所有域对应的值
     *@Param [key-表示所有域对应的键值]
     *@Return T
     *@Author ZhouYouMing
     *@Date 2019/2/27
     *@Time 13:16
     */
    public <T> T redisHashByEntries(String key){
        HashOperations<String, Object, Object> hashOperations = redisTemplate.opsForHash();
        return (T)hashOperations.entries(key);
    }
    //***************hash操作********************/

    //***************set操作********************/
    /**
     *@Description添加元素
     *@Param [key, objects]
     *@Return void
     *@Author ZhouYouMing
     *@Date 2019/2/27
     *@Time 14:00
     */
    public void redisSetByAdd(String key,Object ... objects){
        SetOperations<String, Object> setOperations = redisTemplate.opsForSet();
        for(Object object : objects){
            setOperations.add(key,objects);
        }
    }

    /**
     *@Description 判断元素是否存在
     *@Param [key, object]
     *@Return boolean
     *@Author ZhouYouMing
     *@Date 2019/2/27
     *@Time 13:58
     */
    public boolean redisSetByIsMember(String key,Object object){
        SetOperations<String, Object> setOperations = redisTemplate.opsForSet();
        return setOperations.isMember(key,object);
    }

    /**
     *@Description 获取该key下所有元素
     *@Param [key]
     *@Return T
     *@Author ZhouYouMing
     *@Date 2019/2/27
     *@Time 18:51
     */
    public <T> T redisSetBymembers(String key){
        SetOperations<String, Object> setOperations = redisTemplate.opsForSet();
        return (T)setOperations.members(key);
    }
    //***************set操作********************/

    //***************zset操作********************/
    /**
     *@Description 添加元素
     *@Param [key-键值, o1-对象值, o2-分值]
     *@Return boolean
     *@Author ZhouYouMing
     *@Date 2019/2/27
     *@Time 18:56
     */
    public boolean redisZSetByZAdd(String key,Object o1,double o2){
        ZSetOperations<String, Object> zSetOperations = redisTemplate.opsForZSet();
        return zSetOperations.add(key,o1,o2);
    }

    /**
     *@Description 返回有序集 key 中，指定区间内的成员
     *@Param [key, o1, o2]
     *@Return java.util.Set<java.lang.Object>
     *@Author ZhouYouMing
     *@Date 2019/3/1
     *@Time 13:05
     */
    public Set<Object> redisZSetByZRange(String key,Long o1,Long o2){
        ZSetOperations<String, Object> zSetOperations = redisTemplate.opsForZSet();
        return zSetOperations.range(key,o1,o2);
    }

    /**
     *@Description 返回有序集 key 中，指定区间内的成员和分值
     *@Param [key, o1, o2]
     *@Return java.util.Map<java.lang.Object,java.lang.Double>
     *@Author ZhouYouMing
     *@Date 2019/3/1
     *@Time 13:29
     */
    public Map<Object,Double> redisZSetByRangeWithScores(String key,Long o1,Long o2){
        ZSetOperations<String, Object> zSetOperations = redisTemplate.opsForZSet();
        Set<ZSetOperations.TypedTuple<Object>> result =  zSetOperations.rangeWithScores(key,o1,o2);
        Map<Object,Double> map = new HashMap<Object,Double>();
        for(ZSetOperations.TypedTuple<Object> zot : result){
              map.put(zot.getValue(), zot.getScore());
        }
        return map;
    }
    //***************zset操作********************/

    //***************setNX*********************/
    public boolean lock(String key,Long expire) {
        String lock = "LOCK_PREFIX" + key;
        return (Boolean) redisTemplate.execute((RedisCallback) connection -> {
            long expireAt = System.currentTimeMillis() + expire + 1;
            Boolean acquire = connection.setNX(lock.getBytes(), String.valueOf(expireAt).getBytes());
            if (acquire) {
                return true;
            } else {
                byte[] value = connection.get(lock.getBytes());
                if (Objects.nonNull(value) && value.length > 0) {
                    long expireTime = Long.parseLong(new String(value));
                    if (expireTime < System.currentTimeMillis()) {
                        byte[] oldValue = connection.getSet(lock.getBytes(), String.valueOf(System.currentTimeMillis() + expire + 1).getBytes());
                        return Long.parseLong(new String(oldValue)) < System.currentTimeMillis();
                    }
                }
            }
            return false;
        });
    }
    //***************setNX*********************/
}
