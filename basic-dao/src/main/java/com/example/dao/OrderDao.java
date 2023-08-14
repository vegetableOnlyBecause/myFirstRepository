package com.example.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.condition.OrderCondition;
import com.example.model.OrderDO;
import com.github.pagehelper.PageInfo;

/**
 * @title:
 * @author: vegetableOnlyBecause
 * @date 2023/8/9 19:01
 * @description:
 */
public interface OrderDao extends IService<OrderDO> {

    PageInfo<OrderDO> listInfo(OrderCondition condition);
}
