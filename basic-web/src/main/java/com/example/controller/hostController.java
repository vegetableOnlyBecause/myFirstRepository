package com.example.controller;

import com.example.CupService;
import com.example.common.redis.RedisOperator;
import com.example.response.OperationResult;
import com.example.dao.CupDao;
import com.example.model.Cup;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
public class hostController {

    @Resource
    private RedisOperator redisOperator;
    @Resource
    private CupService cupService;


    @RequestMapping(value = "/v1/test")
    public OperationResult<Object> getCache(int page, int pageSize) throws InterruptedException {
        PageInfo<Cup> cupPageInfo = cupService.listCupByAddr("11",page,pageSize);
        return OperationResult.succ(cupPageInfo);
    }
}
