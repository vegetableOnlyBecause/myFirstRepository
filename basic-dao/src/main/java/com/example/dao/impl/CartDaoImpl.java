package com.example.dao.impl;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.dao.CartDao;
import com.example.mapper.CartDOMapper;
import com.example.model.CartDO;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

/**
 * @title:
 * @author: vegetableOnlyBecause
 * @date 2022/11/23 10:45
 * @description:
 */
@Repository
public class CartDaoImpl extends ServiceImpl<CartDOMapper, CartDO> implements CartDao {

    @Override
    public List<CartDO> listByUserId(Integer userId) {
        List<CartDO> dos = this.baseMapper.selectList(
                new LambdaQueryWrapper<CartDO>().eq(CartDO::getUserId, userId));
        return CollectionUtils.isNotEmpty(dos) ? dos : Collections.emptyList();
    }


    @Override
    public void del(Integer userId, Integer goodId) {
        this.baseMapper.delete(new LambdaQueryWrapper<CartDO>()
                .eq(CartDO::getUserId, userId)
                .eq(CartDO::getGoodsId, goodId));
    }

}
