package com.example.order.impl;

import com.example.commodity.util.PageInfoUtils;
import com.example.condition.OrderCondition;
import com.example.dao.OrderDao;
import com.example.model.OrderDO;
import com.example.order.OrderService;
import com.example.order.dto.OrderDTO;
import com.example.order.util.OrderUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @title:
 * @author: vegetableOnlyBecause
 * @date 2022/11/11 15:32
 * @description:
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;

    @Override
    public PageInfo<OrderDTO> listInfo(OrderCondition condition) {
        PageInfo<OrderDO> dos = orderDao.listInfo(condition);
        return PageInfoUtils.pageInfoTrans(dos, OrderUtils::do2dto);
    }
}
