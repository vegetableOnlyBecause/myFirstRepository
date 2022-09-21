package com.example.common.redis;

import redis.clients.jedis.params.SetParams;

import java.util.Collections;

public class RedisLocker {
    /**
     * 加锁成功
     */
    private static final String LOCK_SUCCESS = "OK";
    /**
     * 解锁成功
     */
    private static final Long RELEASE_SUCCESS = 1L;
    /**
     * 如果没有获取到锁,每10ms重试一次
     */
    private static final int DEFAULT_ACQUIRY_RETRY_MILLIS = 10;
    /**
     *  加锁值
     */
    private static final String  LOCK_VALUE = "1";

    /**
     * @Title 加锁
     * @Description 如果获取锁失败,每10ms重试一次,重试最大次数为3,否则返回false
     * @Author long.yuan
     * @Date 2020/2/15 14:49
     * @Param [key, value, expireTime]
     * @return boolean
     **/
    public static boolean lock(String key, long expireTime) throws InterruptedException {
        int count = 0;
        int countMax = 3;
        while (count < countMax){
            SetParams params = new SetParams();
            // NX表示仅在key不存在时才能设置成功
            params.nx();
            // EX表示单位是秒，PX表示单位是毫秒
            params.px(expireTime);
            if (LOCK_SUCCESS.equals(JedisUtil.jedis.set(key, LOCK_VALUE, params))) {
                return true;
//            }else{
//                // 如果没有拿到锁,每10ms重试一次,最多重试3次
//                TimeUnit.MILLISECONDS.sleep(DEFAULT_ACQUIRY_RETRY_MILLIS);
//                count++;
            }
        }
        return false;
    }

    /**
     * @Title 解锁
     * @Description 解锁时与传进来的value作对比,看是否是自己加的锁,如果是自己加的锁再删除
     * @Author long.yuan
     * @Date 2020/2/15 14:55
     * @Param [key, value]
     * @return boolean
     **/
    public static boolean unLock(String key, String value) {
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object result = JedisUtil.jedis.eval(script, Collections.singletonList(key), Collections.singletonList(value));

        if (RELEASE_SUCCESS.equals(result)) {
            return true;
        }
        return false;
    }

}
