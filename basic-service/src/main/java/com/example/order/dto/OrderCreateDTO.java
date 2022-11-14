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

    private String buyerUserId;

    private String sellerUserId;

    private String goodsId;

    private Integer goodsNum;

    private Long totalAmount;

    private Long payAmount;

    private String orderStatus = OrderStatusEnums.Init.toString();

    private String orderType = OrderTypeEnums.Common.toString();

    private Date payTime;

    private Date createTime = new Date();

    private Date updateTime = new Date();

    private String ext;
}
