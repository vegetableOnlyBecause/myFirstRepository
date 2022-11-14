package com.example.dao;

import com.example.condition.OrderCondition;
import com.example.mapper.OrderDOMapper;
import com.example.model.OrderDO;
import com.example.model.OrderDOExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @title: 订单持久层操作类
 * @author: vegetableOnlyBecause
 * @date 2022/11/11 15:22
 * @description:
 */
@Repository
public class OrderDao {

    @Resource
    private OrderDOMapper orderDOMapper;

    public PageInfo<OrderDO> listInfo(OrderCondition condition) {
        OrderDOExample example = new OrderDOExample();
        OrderDOExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(condition.getOrderId())) {
            criteria.andOrderIdEqualTo(condition.getOrderId());
        }
        if (StringUtils.isNotBlank(condition.getBuyerUserId())) {
            criteria.andBuyerUserIdEqualTo(condition.getBuyerUserId());
        }
        if (StringUtils.isNotBlank(condition.getSellerUserId())) {
            criteria.andSellerUserIdEqualTo(condition.getSellerUserId());
        }
        if (CollectionUtils.isNotEmpty(condition.getOrderStatus())) {
            criteria.andOrderStatusIn(condition.getOrderStatus());
        }
        if (CollectionUtils.isNotEmpty(condition.getOrderType())) {
            criteria.andOrderTypeIn(condition.getOrderType());
        }
        if (null != condition.getStartTime()) {
            criteria.andCreateTimeGreaterThanOrEqualTo(condition.getStartTime());
        }
        if (null != condition.getEndTime()) {
            criteria.andCreateTimeLessThanOrEqualTo(condition.getEndTime());
        }
        example.setOrderByClause(condition.getSortField()
                + " " + condition.getSortType());
        condition.initPageInfo();
        PageHelper.startPage(condition.getPage(), condition.getPageSize());
        List<OrderDO> dos = orderDOMapper.selectByExample(example);
        return new PageInfo<>(Optional.ofNullable(dos).orElse(Collections.emptyList()));
    }

    public OrderDO getById(String orderId) {
        OrderDOExample example = new OrderDOExample();
        OrderDOExample.Criteria criteria = example.createCriteria();
        criteria.andOrderIdEqualTo(orderId);
        List<OrderDO> dos = orderDOMapper.selectByExample(example);
        return Optional.ofNullable(dos.get(0)).orElse(null);
    }

    public String save(OrderDO orderDO) {
        orderDO.setOrderId(UUID.randomUUID().toString());
        orderDOMapper.insertSelective(orderDO);
        return orderDO.getOrderId();
    }

    public void updateStatus(String orderId, String status) {
        OrderDO order = getById(orderId);
        order.setOrderStatus(status);
        OrderDOExample example = new OrderDOExample();
        OrderDOExample.Criteria criteria = example.createCriteria();
        criteria.andOrderIdEqualTo(orderId);
        orderDOMapper.updateByExampleSelective(order, example);
    }
}
