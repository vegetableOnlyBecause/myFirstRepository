package com.example.order.util;

import com.example.common.utils.OprUtils;
import com.example.model.OrderDO;
import com.example.order.dto.OrderCreateDTO;
import com.example.order.dto.OrderDTO;
import com.example.user.util.UserUtils;

import java.util.function.Consumer;

/**
 * @title:
 * @author: vegetableOnlyBecause
 * @date 2022/11/11 15:34
 * @description:
 */
public class OrderUtils {

    public static OrderDTO do2dto(OrderDO order) {
        return OprUtils.copyModel2Model(order, new OrderDTO());
    }

    public static OrderDO dto2do(OrderCreateDTO dto) {
        Consumer<OrderDO> consumer = order -> {
            order.setId(UserUtils.initId());
            order.setStatus(2);
        };
        return OprUtils.model2Model(dto, new OrderDO(), consumer);
    }
}
