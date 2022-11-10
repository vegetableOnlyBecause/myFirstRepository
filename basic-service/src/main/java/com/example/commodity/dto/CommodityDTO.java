package com.example.commodity.dto;

import lombok.Data;

import java.util.Date;

/**
 * @title: 商品DTO
 * @author: vegetableOnlyBecause
 * @date 2022/11/10 17:44
 * @description:
 */
@Data
public class CommodityDTO {

    private String commodityId;

    private String commodityName;

    private String categoryId;

    private Long originalPrice;

    private Long salePrice;

    private String detail;
}
