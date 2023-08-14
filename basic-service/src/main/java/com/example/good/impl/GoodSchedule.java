package com.example.good.impl;

import com.example.condition.GoodCondition;
import com.example.dao.GoodDao;
import com.example.dao.impl.GoodDaoImpl;
import com.example.model.GoodDO;
import com.github.pagehelper.PageInfo;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @title: 商品定时任务
 * @author: vegetableOnlyBecause
 * @date 2022/12/28 16:30
 * @description: 定时任务
 */
@Component
public class GoodSchedule {

    @Resource
    private GoodDao goodDao;

    @Scheduled(cron = "0 */1 * * * ?")
    public void updateGoodStatus(){
        GoodCondition condition = new GoodCondition();
        PageInfo<GoodDO> goodDOPageInfo = goodDao.listInfo(condition);
        List<GoodDO> goods = goodDOPageInfo.getList();
        for (GoodDO good : goods) {
            if (new Date().after(good.getEndTime())) {
                good.setStatus(0);
                goodDao.updateById(good);
            }
        }
    }
}
