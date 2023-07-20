package com.example.dao;

import com.example.mapper.CartDOMapper;
import com.example.model.CartDO;
import com.example.model.CartDOExample;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * @title:
 * @author: vegetableOnlyBecause
 * @date 2022/11/23 10:45
 * @description:
 */
@Repository
public class CartDao {

    @Resource
    private CartDOMapper cartDOMapper;

    public List<CartDO> listByUserId(Integer userId) {
        CartDOExample example = new CartDOExample();
        CartDOExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        List<CartDO> dos = cartDOMapper.selectByExample(example);
        return CollectionUtils.isNotEmpty(dos) ? dos : Collections.emptyList();
    }

    public void save(CartDO cartDO) {
        cartDOMapper.insertSelective(cartDO);
    }

    public void del(Integer userId, Integer goodId) {
        CartDOExample example = new CartDOExample();
        CartDOExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId).andGoodsIdEqualTo(goodId);
        cartDOMapper.deleteByExample(example);
    }
}
