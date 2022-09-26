package com.example.exception;

import lombok.Data;

import java.io.Serializable;

/**
 * @title: 业务错误
 * @author: vegetableOnlyBecause
 * @date 2022/9/26 16:52
 * @description:
 */
@Data
public class BusinessException extends Exception implements Serializable {
    private static final long serialVersionUID = -5261487481340401779L;

    private String msgCode;

    private String msg;

}
