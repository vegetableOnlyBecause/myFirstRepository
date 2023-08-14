package com.example.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.condition.GoodCondition;
import com.example.model.GoodDO;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @title:
 * @author: vegetableOnlyBecause
 * @date 2023/8/9 16:30
 * @description:
 */
public interface GoodDao extends IService<GoodDO> {

    List<GoodDO> listByType(int typeId);

    PageInfo<GoodDO> listInfo(GoodCondition condition);

}
