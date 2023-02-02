package com.example.good.util;

import com.example.common.utils.OprUtils;
import com.example.good.dto.CartDTO;
import com.example.good.dto.CartOprParamDTO;
import com.example.good.dto.CommentDTO;
import com.example.model.CartDO;
import com.example.model.CommentDO;

/**
 * @title:
 * @author: vegetableOnlyBecause
 * @date 2022/11/23 10:42
 * @description:
 */
public class CartUtils {

    public static CartDTO do2dto(CartDO cart) {
        return OprUtils.copyModel2Model(cart, new CartDTO());
    }

    public static CommentDTO do2dto(CommentDO comment) {
        return OprUtils.copyModel2Model(comment, new CommentDTO());
    }

    public static CartDO dto2do(CartOprParamDTO dto) {
        return OprUtils.copyModel2Model(dto, new CartDO());
    }
}
