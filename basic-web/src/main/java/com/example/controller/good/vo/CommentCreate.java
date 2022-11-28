package com.example.controller.good.vo;

import lombok.Data;

/**
 * @title:
 * @author: vegetableOnlyBecause
 * @date 2022/11/21 15:27
 * @description:
 */
@Data
public class CommentCreate {

    private Integer goodId;

    private Integer userId;

    private String comments;
}
