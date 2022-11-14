package com.example.order.util;

import com.example.commodity.dto.CommodityDTO;
import com.example.model.OrderDO;
import com.example.order.dto.OrderCreateDTO;
import com.example.order.dto.OrderDTO;
import com.example.user.dto.UserDTO;
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
        UserDTO buyer = new UserDTO();
        buyer.setUserId(order.getBuyerUserId());
        UserDTO seller = new UserDTO();
        seller.setUserId(order.getSellerUserId());
        return dto;
    }

    public static OrderDO dto2do(OrderCreateDTO dto, CommodityDTO good) {
        OrderDO order = new OrderDO();
        BeanUtils.copyProperties(dto, order);
        order.setTotalAmount(dto.getGoodsNum() * good.getOriginalPrice());
        order.setTotalAmount(dto.getGoodsNum() * good.getSalePrice());
        return order;
    }
}
