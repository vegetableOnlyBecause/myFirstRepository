package com.example.controller.commodity.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @title: 商品创建信息
 * @author: vegetableOnlyBecause
 * @date 2022/11/10 18:58
 * @description:
 */
@Data
public class CommodityCreate implements Serializable {

    private static final long serialVersionUID = -5608164219658996521L;

    @NotBlank(message = "商品名称不能为空")
    private String commodityName;

    @NotBlank(message = "商品类目不能为空")
    private String categoryId;

    @NotNull(message = "原始价格不能为空")
    private Long originalPrice;

    @NotNull(message = "销售价格不能为空")
    private Long salePrice;

    private String detail;
}
