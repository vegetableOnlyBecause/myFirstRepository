package com.example.aop;

import com.alibaba.fastjson.JSON;
import com.example.user.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.function.Function;

/**
 * @title: 数据库查询操作枚举
 * @author: vegetableOnlyBecause
 * @date 2022/9/22 14:32
 * @description: 区分不同数据库查询操作
 */

@Getter
@AllArgsConstructor
public enum CacheAopEnums {
    /**
     * 根据Id获取用户.
     */
    GET_USER_BY_ID(str -> JSON.parseObject(str, UserDTO.class)),
    ;

    private final Function<String, Object> func;

}
