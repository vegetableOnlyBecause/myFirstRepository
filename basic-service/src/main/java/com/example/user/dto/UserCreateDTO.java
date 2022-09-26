package com.example.user.dto;

import lombok.Data;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Date;

/**
 * @title: 创建用户所需信息类
 * @author: vegetableOnlyBecause
 * @date 2022/9/26 15:43
 * @description:
 */
@Data
public class UserCreateDTO implements Serializable {

    private static final long serialVersionUID = 229156637375701790L;

    private String userName;

    private String password;

    private String nickName;

    private String phoneNumber;

    private Date birthday;

    private Date email;
}
