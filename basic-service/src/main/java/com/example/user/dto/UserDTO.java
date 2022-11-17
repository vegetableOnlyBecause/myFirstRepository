package com.example.user.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * @title: 用户实体类DTO
 * @author: vegetableOnlyBecause
 * @date 2022/9/26 14:47
 * @description:
 */
@Data
public class UserDTO implements Serializable {
    private static final long serialVersionUID = -2625741234379947045L;

    private Integer id;

    private String userName;

    private String password;

    private String phone;

    private String qq;

    private Integer goodsNum;

    private Integer power;

    private Float coin;

    private Float credit;

    private Date createTime;
}
