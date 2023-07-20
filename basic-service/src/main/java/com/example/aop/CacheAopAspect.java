package com.example.aop;

import com.alibaba.fastjson.JSON;
import com.example.common.cache.GuavaCache;
import com.example.common.redis.RedisOperator;
import com.google.common.cache.Cache;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Optional;

/**
 * @title: 缓存处理切面操作
 * @author: vegetableOnlyBecause
 * @date 2022/9/22 14:36
 * @description:
 */
@Slf4j
@Aspect
@Component
public class CacheAopAspect {
    /**
     * redis对象.
     */
    @Resource
    private RedisOperator redisOperator;
    /**
     * 本地内存GuavaCache对象.
     */
    @Resource
    GuavaCache guavaCache;

    /**
     * 分隔符.
     */
    public static final String symbol = "#";

    /**
     * 切点.
     */
    @Pointcut("@annotation(com.example.aop.CacheAop)")
    public void cacheAopPointCut(){}

    /**
     * 环绕方法
     * @param joinPoint joinPoint
     * @param cacheAop cacheAop
     * @return 执行结果
     */
    @Around("cacheAopPointCut() && @annotation(cacheAop)")
    public Object cacheAopOprAround(ProceedingJoinPoint joinPoint, CacheAop cacheAop) {
        Object result = null;
        CacheAopEnums enums = cacheAop.type();
        String key = getKey(enums, joinPoint.getArgs());
        // 本地缓存操作
        Cache<String, Object> cache = guavaCache.getCache();
        if (cacheAop.needLocalCache()){
            result = getValueByLocal(key, cache);
        }
        // redis缓存操作
        result = Optional.ofNullable(result)
                .orElseGet(() -> getValueByRedis(key, cacheAop, cache));
        // 数据库操作
        result = null != result ? result :
                getValueByDB(key, cacheAop, joinPoint, cache);
        return result;
    }

    /**
     * 获取缓存key
     * @param type 操作类型
     * @param args 参数列表
     * @return 缓存key
     */
    private String getKey(CacheAopEnums type, Object[] args) {
        StringBuilder key = new StringBuilder(type.toString());
        Iterator<Object> iterator = Arrays.stream(args).iterator();
        while (iterator.hasNext()) {
            key.append(symbol).append(iterator.next());
        }
        return key.toString();
    }

    /**
     * 根据本地缓存获取值
     * @param key 缓存key
     * @param cache 本地缓存对象
     * @return 值
     */
    private Object getValueByLocal(String key, Cache<String, Object> cache) {
        return  cache.getIfPresent(key);
    }

    /**
     * 根据redis获取值
     * @param key 缓存key
     * @param cacheAop 切面aop
     * @param cache 本地缓存对象
     * @return 值
     */
    private Object getValueByRedis(String key, CacheAop cacheAop,
                                   Cache<String, Object> cache) {
        String info = redisOperator.get(key);
        CacheAopEnums enums = cacheAop.type();
        if (StringUtils.isNotBlank(info)) {
            Object result = enums.getFunc().apply(info);
            if (cacheAop.needLocalCache()) {
                cache.put(key, result);
            }
            return result;
        }
        return null;
    }

    /**
     * 根据数据库获取值
     * @param key 缓存key
     * @param cacheAop  切面aop
     * @param joinPoint joinPoint
     * @param cache 本地缓存对象
     * @return 值
     */
    private Object getValueByDB(String key, CacheAop cacheAop,
                                ProceedingJoinPoint joinPoint,
                                Cache<String, Object> cache) {
        Object result = null;
        try {
            result = joinPoint.proceed();
            redisOperator.set(key, JSON.toJSONString(result), cacheAop.expireTime());
            if (cacheAop.needLocalCache()){
                cache.put(key,result);
            }
        } catch (Throwable e) {
            log.error("缓存切面执行失败, e:{}", e.toString());
        }
        return result;
    }
}
