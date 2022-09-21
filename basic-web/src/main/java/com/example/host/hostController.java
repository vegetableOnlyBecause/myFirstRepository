package com.example.host;

import com.example.cache.GuavaCacheTest;
import com.example.common.redis.RedisLocker;
import com.example.common.redis.RedisOperator;
import com.example.common.response.OperationResult;
import com.example.dao.CupDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
public class hostController {

    @Resource
    private RedisOperator redisOperator;


    @RequestMapping(value = "/v1/test")
    public OperationResult<Object> getCache() throws InterruptedException {

        log.info("info");
        log.error("error");
        if (1==1){
            throw new InterruptedException("123");
        }
        redisOperator.set("abc","111");
        boolean abc = RedisLocker.lock("abcc", 30000);
        return OperationResult.succ(abc);
    }
}
