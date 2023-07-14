package com.example.order.dto;

import lombok.Data;

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
