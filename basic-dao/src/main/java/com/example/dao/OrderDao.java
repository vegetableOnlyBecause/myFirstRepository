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
        // 设置查询条件
        Optional.ofNullable(condition.getGoodId()).ifPresent(criteria::andGoodsIdEqualTo);
        Optional.ofNullable(condition.getOrderId()).ifPresent(criteria::andIdEqualTo);
        Optional.ofNullable(condition.getBuyerId()).ifPresent(criteria::andBuyerIdEqualTo);
        Optional.ofNullable(condition.getSalerId()).ifPresent(criteria::andSalerIdEqualTo);
        Optional.ofNullable(condition.getStartTime()).ifPresent(criteria::andCreateTimeGreaterThanOrEqualTo);
        Optional.ofNullable(condition.getEndTime()).ifPresent(criteria::andCreateTimeLessThanOrEqualTo);
        if (CollectionUtils.isNotEmpty(condition.getOrderStatus())) {
            criteria.andStatusIn(condition.getOrderStatus());
        }

        example.setOrderByClause(condition.getSortField()
                + " " + condition.getSortType());
        condition.initPageInfo();
        PageHelper.startPage(condition.getPage(), condition.getPageSize());
        List<OrderDO> dos = orderDOMapper.selectByExample(example);
       return new PageInfo<>(CollectionUtils.isNotEmpty(dos) ? dos : Collections.emptyList());
    }

    public OrderDO getById(Integer orderId) {
        OrderDOExample example = new OrderDOExample();
        OrderDOExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(orderId);
        List<OrderDO> dos = orderDOMapper.selectByExample(example);
        return CollectionUtils.isNotEmpty(dos) ? dos.get(0) : null;
    }

    public Integer save(OrderDO orderDO) {
        orderDOMapper.insertSelective(orderDO);
        return orderDO.getId();
    }

    public void del(Integer id) {
        orderDOMapper.deleteByPrimaryKey(id);
    }

}
