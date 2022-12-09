package com.example.controller.order.util;

import com.example.controller.order.vo.OrderCreate;
import com.example.controller.order.vo.OrderVO;
import com.example.controller.user.util.UserTransUtils;
import com.example.good.dto.GoodDTO;
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

    public static OrderVO dto2vo(GoodDTO dto, OrderDTO order) {
        if (null == dto) {
            return null;
        }
        OrderVO vo = new OrderVO();
        BeanUtils.copyProperties(dto, vo);
        vo.setId(order.getId());
        return vo;
    }

}
