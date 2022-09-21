package com.example.common.redis;

import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;

@Repository
public class JedisUtil {
    /**
     * 初始化Jedsi实例
     */
    public static final Jedis jedis;
    static{
        jedis = new Jedis("127.0.0.1",6379);
        //jedis.auth("");
    }

}
