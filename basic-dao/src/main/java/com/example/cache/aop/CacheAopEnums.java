package com.example.cache.aop;

import com.example.model.Cup;

/**
 * @title: 数据库查询操作枚举
 * @author: vegetableOnlyBecause
 * @date 2022/9/22 14:32
 * @description: 区分不同数据库查询操作
 */

public enum CacheAopEnums {
    GET_CUP_BY_ID(Cup.class, "object"),
    LIST_CUP_BY_NAME(Cup.class, "list"),
    ;

    private Class<?> clazz; // 相应的类信息
    private String infoType; // 相应信息类型, 目前支持object/list

    CacheAopEnums(Class<?> clazz, String infoType) {
        this.clazz = clazz;
        this.infoType = infoType;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public String getInfoType() {
        return infoType;
    }
}
