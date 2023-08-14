package com.example.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.model.CartDO;

import java.util.List;

/**
 * @title:
 * @author: vegetableOnlyBecause
 * @date 2023/8/7 17:28
 * @description:
 */
public interface CartDao extends IService<CartDO> {

    List<CartDO> listByUserId(Integer userId);

    void del(Integer userId, Integer goodId);
}
