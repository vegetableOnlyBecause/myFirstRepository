package com.example.order.impl;

import com.example.good.GoodService;
import com.example.good.dto.GoodDTO;
import com.example.good.util.PageInfoUtils;
import com.example.condition.OrderCondition;
import com.example.dao.OrderDao;
import com.example.enums.OrderStatusEnums;
import com.example.model.OrderDO;
import com.example.order.OrderService;
import com.example.order.dto.OrderCreateDTO;
import com.example.order.dto.OrderDTO;
import com.example.order.util.OrderUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Resource
    private GoodService goodService;


    @Override
    public Integer save(OrderCreateDTO dto) {
        GoodDTO good = goodService.getById(dto.getGoodsId());
        // todo:如果商品不存在则抛错
        OrderDO orderDO = OrderUtils.dto2do(dto);
        return orderDao.save(orderDO);
    }

    @Override
    public PageInfo<OrderDTO> listInfo(OrderCondition condition) {
        PageInfo<OrderDO> dos = orderDao.listInfo(condition);
        return PageInfoUtils.pageInfoTrans(dos, OrderUtils::do2dto);
    }

    @Override
    @Transactional
    public void del(Integer id) {
        OrderDO order = orderDao.getById(id);
        orderDao.del(id);
        goodService.lessInventory(order.getGoodsId(), -1);
    }
}
