package com.example.order.dto;

import com.example.enums.OrderStatusEnums;
import com.example.enums.OrderTypeEnums;
import lombok.Data;

import java.util.Date;

/**
 * @title:
 * @author: vegetableOnlyBecause
 * @date 2022/11/11 17:25
 * @description:
 */
@Data
public class OrderCreateDTO {

    private Integer salerId;

    private Integer buyerId;

    private Integer goodsId;
}
