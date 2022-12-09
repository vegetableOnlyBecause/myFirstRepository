package com.example.order.util;

import com.example.good.dto.GoodDTO;
import com.example.model.OrderDO;
import com.example.order.dto.OrderCreateDTO;
import com.example.order.dto.OrderDTO;
import com.example.user.dto.UserDTO;
import com.example.user.util.UserUtils;
import org.springframework.beans.BeanUtils;

/**
 * @title:
 * @author: vegetableOnlyBecause
 * @date 2022/11/11 15:34
 * @description:
 */
public class OrderUtils {

    public static OrderDTO do2dto(OrderDO order) {
        if (null == order) {
            return null;
        }
        OrderDTO dto = new OrderDTO();
        BeanUtils.copyProperties(order, dto);
        return dto;
    }

    public static OrderDO dto2do(OrderCreateDTO dto) {
        OrderDO order = new OrderDO();
        BeanUtils.copyProperties(dto, order);
        order.setId(UserUtils.initId());
        order.setStatus(2);
        return order;
    }
}
