package com.example.filter.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;
import java.util.function.BiFunction;

/**
 * @title: 时间类型处理枚举
 * @author: vegetableOnlyBecause
 * @date 2023/7/19 8:41
 * @description:
 */
@Getter
@AllArgsConstructor
public enum TimeOprEnums {
    /**
     * Date1小于Date2.
     */
    before(Date::before),
    /**
     * Date1小于等于Date2.
     */
    beforeAndEquals((t1,t2) -> t1.before(t2) || t1.equals(t2)),
    /**
     * Date1大于Date2.
     */
    after(Date::after),
    /**
     * Date1大于等于Date2.
     */
    afterAndEquals((t1,t2) -> t1.after(t2) || t1.equals(t2)),
    /**
     * Date1等于Date2.
     */
    equals(Date::equals),
    ;

    private final BiFunction<Date, Date, Boolean> biFunction;

}
