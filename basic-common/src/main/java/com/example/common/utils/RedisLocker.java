package com.example.common.utils;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

/**
 * @title:
 * @author: vegetableOnlyBecause
 * @date 2023/11/24 15:48
 * @description:
 */
@Component
public class RedisLocker {
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 分布式锁对应Value默认值
     */
    private static final String LOCK_VALUE = "1";

    /**
     * 获取分布式锁的Lua脚本
     */
    private static final String LOCK_LUA_SCRIPT = "if redis.call('setnx',KEYS[1],ARGV[1]) == 1 then if redis.call('get',KEYS[1]) == ARGV[1] then return redis.call('expire',KEYS[1],ARGV[2]) else return 0 end else return 0 end";

    /**
     * 释放分布式锁的Lua脚本
     */
    private static final String UNLOCK_LUA_SCRIPT = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";

    /**
     * 获取分布式锁成功的值
     */
    private static final String TRY_LOCK_SUCCESS = "1";

    /**
     * 尝试获取分布式锁
     *
     * @param lockKey       锁的唯一标识
     * @param requestId     客户线程的唯一标识，防止其他线程把key删除
     * @param expireSeconds 分布式锁默认租用时间，单位秒
     * @return 锁获取成功与否
     */
    public boolean tryLock(String lockKey, String requestId, int expireSeconds) {
        return redisTemplate.opsForValue().setIfAbsent(lockKey, requestId, expireSeconds, TimeUnit.SECONDS);
    }

    /**
     * 尝试获取分布式锁
     *
     * @param lockKey       锁的唯一标识
     * @param expireSeconds 分布式锁默认租用时间，单位秒
     * @return 锁获取成功与否
     */
    public boolean tryLock(String lockKey, int expireSeconds) {
        return tryLock(lockKey, LOCK_VALUE, expireSeconds);
    }

    /**
     * 释放分布式锁
     *
     * @param lockKey   锁的唯一标识
     * @param requestId 客户线程的唯一标识，防止当前线程把其他线程的锁给释放掉
     */
    public void unLock(String lockKey, String requestId) {
        DefaultRedisScript<Long> script = new DefaultRedisScript<>(UNLOCK_LUA_SCRIPT, Long.class);
        Object value = redisTemplate.execute(script, Collections.singletonList(lockKey), requestId);
        System.out.println();
    }


}
