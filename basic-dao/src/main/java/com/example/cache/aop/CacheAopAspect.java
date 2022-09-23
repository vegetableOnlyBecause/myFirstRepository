package com.example.cache.aop;

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
    @Resource
    private RedisOperator redisOperator;
    @Resource
    GuavaCache guavaCache;

    private final String symbol = "#";
    private final String info_type_list="list";

    @Pointcut("@annotation(com.example.cache.aop.CacheAop)")
    public void cacheAopPointCut(){}

    @Around("cacheAopPointCut() && @annotation(cacheAop)")
    public Object  cacheAopOprAround(ProceedingJoinPoint joinPoint, CacheAop cacheAop) throws Throwable {
        Object result;
        CacheAopEnums enums = cacheAop.operateEnums();
        String key = getKey(enums, joinPoint);
        // 本地缓存操作
        Cache<String, Object> cache = guavaCache.getCache();
        if (cacheAop.needLocalCache()){
            result = cache.getIfPresent(key);
            if (null != result){
                return result;
            }
        }
        // redis缓存操作
        String info = redisOperator.get(key);
        if (StringUtils.isNotBlank(info)){
            result = info_type_list.equals(enums.getInfoType()) ?
                    JSON.parseArray(info, enums.getClazz()) :
                    JSON.parseObject(info, enums.getClazz())
                    ;
            if (cacheAop.needLocalCache()){
                cache.put(key,result);
            }
            return result;
        }
        // 数据库操作
        result = joinPoint.proceed();
        info = JSON.toJSONString(result);
        redisOperator.set(key, info, cacheAop.expireTime());
        if (cacheAop.needLocalCache()){
            cache.put(key,result);
        }
        return result;
    }

    private String getKey(CacheAopEnums enums, ProceedingJoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        StringBuffer key = new StringBuffer(enums.toString());
        key.append(symbol);
        for (int i=0; i<args.length; i++){
            key.append(""+args[i]);
            if (i != args.length -1){
                key.append(symbol);
            }
        }
        return key.toString();
    }
}
