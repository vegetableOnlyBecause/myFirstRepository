package com.example.good.dto;

import lombok.Data;

import java.util.Date;

/**
 * @title: 评论DTO
 * @author: vegetableOnlyBecause
 * @date 2022/11/21 13:32
 * @description:
 */
@Data
public class CommentDTO {

    private Integer id;

    private Integer goodsId;

    private Integer userId;

    private String userName;

    private String comments;

    private Date createTime;
}
