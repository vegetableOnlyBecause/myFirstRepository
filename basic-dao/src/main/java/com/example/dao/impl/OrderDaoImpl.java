package com.example.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.condition.OrderCondition;
import com.example.dao.OrderDao;
import com.example.mapper.OrderDOMapper;
import com.example.model.OrderDO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @title: 订单持久层操作类
 * @author: vegetableOnlyBecause
 * @date 2022/11/11 15:22
 * @description:
 */
@Repository
public class OrderDaoImpl extends ServiceImpl<OrderDOMapper, OrderDO> implements OrderDao {

    @Override
    public PageInfo<OrderDO> listInfo(OrderCondition condition) {
        LambdaQueryWrapper<OrderDO> wrapper = new LambdaQueryWrapper<>();
        // 设置查询条件
        Optional.ofNullable(condition.getGoodId()).ifPresent(goodId -> wrapper.eq(OrderDO::getGoodsId, goodId));
//        Optional.ofNullable(condition.getOrderId()).ifPresent(criteria::andIdEqualTo);
//        Optional.ofNullable(condition.getBuyerId()).ifPresent(criteria::andBuyerIdEqualTo);
//        Optional.ofNullable(condition.getSalerId()).ifPresent(criteria::andSalerIdEqualTo);
//        Optional.ofNullable(condition.getStartTime()).ifPresent(criteria::andCreateTimeGreaterThanOrEqualTo);
//        Optional.ofNullable(condition.getEndTime()).ifPresent(criteria::andCreateTimeLessThanOrEqualTo);
//        if (CollectionUtils.isNotEmpty(condition.getOrderStatus())) {
//            criteria.andStatusIn(condition.getOrderStatus());
//        }
//
//        example.setOrderByClause(condition.getSortField()
//                + " " + condition.getSortType());
//        condition.initPageInfo();
//        PageHelper.startPage(condition.getPage(), condition.getPageSize());
//        List<OrderDO> dos = orderDOMapper.selectByExample(example);
        List<OrderDO> dos = baseMapper.selectList(wrapper);
        return new PageInfo<>(CollectionUtils.isNotEmpty(dos) ? dos : Collections.emptyList());
    }
}
