package com.example.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @title: 缓存处理注解
 * @author: vegetableOnlyBecause
 * @date 2022/9/22 14:28
 * @description: 缓存处理流程统一
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CacheAop {
    CacheAopEnums operateEnums();
    boolean needLocalCache() default false;
    long expireTime() default 300;
}
