package com.example.good;

import com.example.good.dto.CartDTO;
import com.example.good.dto.CartOprParamDTO;

import java.util.List;

/**
 * @title:
 * @author: vegetableOnlyBecause
 * @date 2022/11/23 10:47
 * @description:
 */
public interface CartService {

    List<CartDTO> listByUserId(Integer userId);

    void save(CartOprParamDTO param);

    void del(Integer userId, Integer goodId);
}
