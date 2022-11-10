package com.example.controller.commodity.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @title: 商品展示信息
 * @author: vegetableOnlyBecause
 * @date 2022/11/10 19:34
 * @description:
 */
@Data
public class CommodityVO implements Serializable {
    private static final long serialVersionUID = -5284956025125671579L;

    private String commodityId;

    private String commodityName;

    private String categoryId;

    private Long originalPrice;

    private Long salePrice;

    private String detail;
}
