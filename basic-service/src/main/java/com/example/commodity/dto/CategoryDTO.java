package com.example.commodity.dto;

import lombok.Data;

/**
 * @title: 类目DTO
 * @author: vegetableOnlyBecause
 * @date 2022/11/11 8:53
 * @description:
 */
@Data
public class CategoryDTO {

    private String categoryId;

    private String parentCategoryId;

    private String categoryName;

    private String detail;

}
