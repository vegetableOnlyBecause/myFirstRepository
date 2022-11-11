package com.example.controller.commodity.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @title:
 * @author: vegetableOnlyBecause
 * @date 2022/11/11 9:52
 * @description:
 */
@Data
public class CategoryCreate implements Serializable {
    private static final long serialVersionUID = -9190301385996875706L;

    private String parentCategoryId;

    @NotBlank(message = "类目名称不能为空")
    private String categoryName;

    private String detail;
}
