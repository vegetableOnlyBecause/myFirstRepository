package com.example.commodity.dto;

import lombok.Data;

import java.util.Date;

/**
 * @title: 商品创建信息
 * @author: vegetableOnlyBecause
 * @date 2022/11/10 18:47
 * @description:
 */
@Data
public class CommodityCreateDTO {

    private String commodityId;

    private String commodityName;

    private String categoryId;

    private Long originalPrice;

    private Long salePrice;

    private String detail;

    private Date createTime = new Date();

    private Date updateTime = new Date();
}
