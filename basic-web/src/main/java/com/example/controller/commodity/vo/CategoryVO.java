package com.example.controller.commodity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @title: 类目展示信息
 * @author: vegetableOnlyBecause
 * @date 2022/11/11 9:28
 * @description:
 */
@Data
public class CategoryVO implements Serializable {

    private static final long serialVersionUID = -4508636987683319332L;

    private String categoryId;

    private String parentCategoryId;

    private String categoryName;

    private String detail;

}
