package com.example.order.impl;

import com.example.condition.OrderCondition;
import com.example.dao.OrderDao;
import com.example.good.GoodService;
import com.example.good.dto.GoodDTO;
import com.example.good.util.PageInfoUtils;
import com.example.model.OrderDO;
import com.example.order.OrderService;
import com.example.order.dto.OrderCreateDTO;
import com.example.order.dto.OrderDTO;
import com.example.order.util.OrderUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Objects;

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
    public void save(OrderCreateDTO dto) throws Exception {
        GoodDTO good = goodService.getById(dto.getGoodsId());
        Objects.requireNonNull(good, "商品信息不存在:" + dto.getGoodsId());
        OrderDO orderDO = OrderUtils.dto2do(dto);
        orderDao.save(orderDO);
    }

    @Override
    public PageInfo<OrderDTO> listInfo(OrderCondition condition) {
        PageInfo<OrderDO> dos = orderDao.listInfo(condition);
        return PageInfoUtils.pageInfoTrans(dos, OrderUtils::do2dto);
    }

    @Override
    @Transactional
    public void del(Integer id) throws Exception {
        OrderDO order = orderDao.getById(id);
        orderDao.del(id);
        goodService.lessInventory(order.getGoodsId(), -1);
    }
}
