package com.example.good.impl;

import com.example.common.utils.OprUtils;
import com.example.dao.CartDao;
import com.example.good.CartService;
import com.example.good.dto.CartDTO;
import com.example.good.dto.CartOprParamDTO;
import com.example.good.util.CartUtils;
import com.example.model.CartDO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @title:
 * @author: vegetableOnlyBecause
 * @date 2022/11/23 10:47
 * @description:
 */
@Service
public class CartServiceImpl implements CartService {

    @Resource
    private CartDao cartDao;

    @Override
    public List<CartDTO> listByUserId(Integer userId) {
        List<CartDO> dos = cartDao.listByUserId(userId);
        return OprUtils.models2Models(dos, CartUtils::do2dto);
    }

    @Override
    public void save(CartOprParamDTO param) {
        cartDao.save(CartUtils.dto2do(param));
    }

    @Override
    public void del(Integer userId, Integer goodId) {
        cartDao.del(userId, goodId);
    }
}
