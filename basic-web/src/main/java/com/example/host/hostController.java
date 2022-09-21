package com.example.host;

import com.example.cache.GuavaCacheTest;
import com.example.common.redis.RedisLocker;
import com.example.common.redis.RedisOperator;
import com.example.dao.CupDao;
import com.example.model.Cup;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class hostController {

    @Resource
    private GuavaCacheTest guavaCacheTest;
    @Resource
    private CupDao cupDao;
    @Resource
    private RedisOperator redisOperator;


    @RequestMapping(value = "/v1/test")
    public String getCache() throws InterruptedException {
        String abc1 = redisOperator.get("abc");
        boolean abc = RedisLocker.lock("abc",  300000);
        String abc2 = redisOperator.get("abc");
        return "ok";
    }
}
