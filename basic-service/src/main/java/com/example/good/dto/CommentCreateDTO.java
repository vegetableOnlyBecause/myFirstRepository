package com.example.good.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @title:
 * @author: vegetableOnlyBecause
 * @date 2022/11/21 16:34
 * @description:
 */
@Data
@Builder
public class CommentCreateDTO {

    private Integer goodsId;

    private Integer userId;

    private String userName;

    private String comments;

}
