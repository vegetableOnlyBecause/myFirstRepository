package com.example.controller.good.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @title:
 * @author: vegetableOnlyBecause
 * @date 2022/11/21 14:48
 * @description:
 */
@Data
public class CommentVO implements Serializable {
    private static final long serialVersionUID = -2387073368052101945L;

    private Integer id;

    private Integer goodsId;

    private String userName;

    private String comments;

    private String createTime;
}
