package com.example.common.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Data
@Component("guavaCache")
public class GuavaCache {

    private Cache<String,Object> cache = CacheBuilder.newBuilder()
            //设置缓存初始⼤⼩，应该合理设置，后续会扩容
            .initialCapacity(10)
            //最⼤值
            .maximumSize(100)
            //并发数设置
            .concurrencyLevel(5)
            //缓存过期时间，写⼊后10分钟过期
            .expireAfterWrite(60, TimeUnit.SECONDS)
            //统计缓存命中率
            .recordStats()
            .build();

}
