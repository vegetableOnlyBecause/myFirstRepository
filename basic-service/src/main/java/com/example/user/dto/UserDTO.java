package com.example.user.dto;

import lombok.Data;

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

    private String userId;

    private String userName;

    private String nickName;

    private String phoneNumber;

    private Date birthday;

    private Date email;

    private Date registerTime;
}
