package com.example.good.util;

import com.example.good.dto.CartDTO;
import com.example.good.dto.CartOprParamDTO;
import com.example.model.CartDO;
import org.apache.commons.collections4.CollectionUtils;
import org.checkerframework.checker.units.qual.C;
import org.elasticsearch.client.ml.inference.trainedmodel.langident.LangIdentNeuralNetwork;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @title:
 * @author: vegetableOnlyBecause
 * @date 2022/11/23 10:42
 * @description:
 */
public class CartUtils {

    public static CartDTO do2dto(CartDO cart) {
        if (null == cart) {
            return null;
        }
        CartDTO dto = new CartDTO();
        BeanUtils.copyProperties(cart, dto);
        return dto;
    }

    public static List<CartDTO> dos2dtos(List<CartDO> dos) {
        if (CollectionUtils.isEmpty(dos)) {
            return Collections.emptyList();
        }
        List<CartDTO> dtos = new ArrayList<>();
        for (CartDO cart : dos) {
            CartDTO dto = do2dto(cart);
            if (null != dto) {
                dtos.add(dto);
            }
        }
        return dtos;
    }

    public static CartDO dto2do(CartOprParamDTO dto) {
        if (null == dto) {
            return null;
        }
        CartDO cart = new CartDO();
        BeanUtils.copyProperties(dto, cart);
        return cart;
    }
}
