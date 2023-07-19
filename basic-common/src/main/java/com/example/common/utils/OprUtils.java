package com.example.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @title:
 * @author: vegetableOnlyBecause
 * @date 2022/12/12 14:39
 * @description:
 */
@Slf4j
public class OprUtils {

    private static final String DATE_STRING = "yyyy-MM-dd HH:mm:ss";

    public static <T, U> U copyModel2Model(T model, U result){
        if (null == model) {
            return null;
        }
        BeanUtils.copyProperties(model, result);
        return result;
    }

    public static <T, U> U model2Model(T model, U value, Consumer<U> consumer){
        copyModel2Model(model, value);
        Optional.ofNullable(consumer).ifPresent(x -> x.accept(value));
        return value;
    }

    public static <T, U> List<U> models2Models(List<T> models, Function<T, U> function) {
        if (CollectionUtils.isEmpty(models)) {
            return Collections.emptyList();
        }
        List<U> result = new ArrayList<>();
        models.forEach(model -> Optional.ofNullable(function.apply(model)).ifPresent(result::add));
        return result;
    }

    /**
     * 根据String获取具体枚举类型
     * @param enumsClass 枚举类对象
     * @param string string
     * @param <T> 枚举类
     * @return 具体枚举类型
     */
    public static <T extends Enum<T>> T getEnumFromString(Class<T> enumsClass, String string) {
        if (null != enumsClass && StringUtils.isNotBlank(string)) {
            try {
                return Enum.valueOf(enumsClass, string.trim());
            } catch (IllegalArgumentException ex) {
                log.error("Str 转 枚举类型方法失败, enumsClass:{}, string:{}", enumsClass, string);
            }
        }
        return null;
    }

    /**
     * Str转Date方法
     * @param str string
     * @return Date
     */
    public static Date str2Date(String str) {
        if (StringUtils.isBlank(str)) {
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat(DATE_STRING);
        try {
            return format.parse(str);
        } catch (Exception e) {
            log.error("Str 转 Date 方法失败, str:{}", str);
        }
        return null;
    }
}
