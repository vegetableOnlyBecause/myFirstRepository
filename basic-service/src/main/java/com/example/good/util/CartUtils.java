package com.example.good.util;

import com.example.good.dto.CartDTO;
import com.example.good.dto.CartOprParamDTO;
import com.example.good.dto.CommentDTO;
import com.example.model.CartDO;
import com.example.model.CommentDO;
import org.springframework.beans.BeanUtils;

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

    public static CommentDTO do2dto(CommentDO comment) {
        if (null == comment) {
            return null;
        }
        CommentDTO dto = new CommentDTO();
        BeanUtils.copyProperties(comment, dto);
        return dto;
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
