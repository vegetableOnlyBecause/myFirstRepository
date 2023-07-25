package com.example.common.utils;

import com.alibaba.fastjson.JSON;
import com.example.common.redis.RedisOperator;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * @title: redis操作工具类
 * @author: vegetableOnlyBecause
 * @date 2023/7/25 9:51
 * @description:
 */
@Repository
public class RedisUtils {

    private static RedisOperator redisOpr;

    @Resource
    public void setRedisOperator(RedisOperator redisOperator) {
        RedisUtils.redisOpr = redisOperator;
    }

    /**
     * 通过redis或者底层获取具体对象并写入redis
     * @param key redisKey
     * @param clazz 类型
     * @param time ttl
     * @param supplier 底层查询操作
     * @param <T> 类型
     * @return 具体对象
     */
    public static <T> T get(String key, Class<T> clazz, long time,
                            Supplier<? extends T> supplier) {
        T obj = get(key, clazz);
        if (null != obj) return obj;
        obj = supplier.get();
        redisOpr.set(key, JSON.toJSONString(obj), time);
        return obj;
    }

    /**
     * 通过redis或者底层获取具体对象列表并写入redis
     * @param key redisKey
     * @param clazz 类型
     * @param time ttl
     * @param supplier 底层查询操作
     * @param <T> 类型
     * @return 具体对象列表
     */
    public static <T> List<T> list(String key, Class<T> clazz, long time,
                                   Supplier<? extends List<T>> supplier) {
        List<T> list = list(key, clazz);
        if (CollectionUtils.isNotEmpty(list)) return list;
        list = supplier.get();
        redisOpr.set(key, JSON.toJSONString(list), time);
        return list;
    }

    /**
     * 设置redis的key和value
     * @param key key
     * @param value value
     * @param time ttl
     */
    public static void set(String key, Object value, long time) {
        redisOpr.set(key, JSON.toJSONString(value), time);
    }

    /**
     * 删除redis的某个key
     * @param key key
     */
    public static void del(String key) {
        redisOpr.del(key);
    }

    /**
     * 获取redis中某个key的具体对象
     * @param key key
     * @param clazz 类型
     * @param <T> 类型
     * @return 具体对象
     */
    private static <T> T get(String key, Class<T> clazz) {
        return Optional.ofNullable(redisOpr.get(key))
                .map(v -> JSON.parseObject(v, clazz)).orElse(null);
    }

    /**
     * 获取redis中某个key的具体对象列表
     * @param key key
     * @param clazz 类型
     * @param <T> 类型
     * @return 具体对象列表
     */
    public static <T> List<T> list(String key, Class<T> clazz) {
        return Optional.ofNullable(redisOpr.get(key))
                .map(v -> JSON.parseArray(v, clazz)).orElse(null);
    }
}
