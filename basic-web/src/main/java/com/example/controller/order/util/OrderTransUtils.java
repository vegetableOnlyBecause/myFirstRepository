package com.example.controller.order.util;

import com.example.controller.order.vo.OrderCreate;
import com.example.controller.order.vo.OrderVO;
import com.example.controller.user.util.UserTransUtils;
import com.example.order.dto.OrderCreateDTO;
import com.example.order.dto.OrderDTO;
import org.springframework.beans.BeanUtils;

/**
 * @title:
 * @author: vegetableOnlyBecause
 * @date 2022/11/11 15:40
 * @description:
 */
public class OrderTransUtils {

    public static OrderVO dto2vo(OrderDTO dto) {
        if (null == dto) {
            return null;
        }
        OrderVO vo = new OrderVO();
        BeanUtils.copyProperties(dto, vo);
//        vo.setBuyer(UserTransUtils.dto2vo(dto.getBuyer()));
//        vo.setSeller(UserTransUtils.dto2vo(dto.getSeller()));
        return vo;
    }

    public static OrderCreateDTO vo2dto(OrderCreate vo) {
        if (null == vo) {
            return null;
        }
        OrderCreateDTO dto = new OrderCreateDTO();
        BeanUtils.copyProperties(vo, dto);
        return dto;
    }
}
