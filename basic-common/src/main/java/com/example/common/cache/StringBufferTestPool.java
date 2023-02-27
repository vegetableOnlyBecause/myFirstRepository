package com.example.common.cache;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @title: StringBuffer对象池(测试)
 * @author: vegetableOnlyBecause
 * @date 2023/2/15 16:27
 * @description:
 */
@Component
public class StringBufferTestPool {
    @Resource
    private StringBufferTestFactory factory; //对象创建工厂

    @Bean
    public GenericObjectPool<StringBuffer> objectPool() {
        GenericObjectPoolConfig<StringBuffer> config = new GenericObjectPoolConfig<>();
        // 支持jmx管理扩展
        config.setJmxEnabled(false);
//        config.setJmxNamePrefix("CacheClientPool");
        // 保证获取有效的池对象
        config.setTestOnBorrow(true);
        config.setTestOnReturn(false);
        config.setTestWhileIdle(true);
        return new GenericObjectPool<>(factory, config);
    }
}
