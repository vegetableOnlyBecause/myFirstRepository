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
    /**
     * 时间格式.
     */
    private static final String DATE_STRING = "yyyy-MM-dd HH:mm:ss";
    /**
     * 时间转换 format.
     */
    private static final SimpleDateFormat format = new SimpleDateFormat(DATE_STRING);

    /**
     * Copy T to U.
     * @param model 被copy的对象
     * @param result 复制后的对象
     * @param <T> 被copy的对象的类型
     * @param <U> 复制后的对象类型
     * @return 复制后的对象
     */
    public static <T, U> U copyModel2Model(T model, U result){
        if (null == model) return null;
        BeanUtils.copyProperties(model, result);
        return result;
    }

    /**
     * Copy T to U.
     * @param model 被copy的对象
     * @param value 复制后的对象
     * @param consumer 额外操作
     * @param <T> 被copy的对象的类型
     * @param <U> 复制后的对象类型
     * @return 复制后的对象
     */
    public static <T, U> U model2Model(T model, U value, Consumer<U> consumer){
        copyModel2Model(model, value);
        Optional.ofNullable(consumer).ifPresent(x -> x.accept(value));
        return value;
    }

    /**
     * 转换 T List To U List
     * @param models 被转换的对象列表
     * @param function 转换方法
     * @param <T> 被转换的对象类型
     * @param <U> 转换的对象类型
     * @return 转换的对象列表
     */
    public static <T, U> List<U> models2Models(List<T> models, Function<T, U> function) {
        if (CollectionUtils.isEmpty(models)) return Collections.emptyList();
        List<U> result = new ArrayList<>();
        models.forEach(model -> Optional.ofNullable(function.apply(model)).ifPresent(result::add));
        return result;
    }

    /**
     * 根据String获取具体枚举类型
     * @param clazz 枚举类对象
     * @param str str
     * @param <T> 枚举类
     * @return 具体枚举类型
     */
    public static <T extends Enum<T>> T getEnumByName(Class<T> clazz, String str) {
        if (null != clazz && StringUtils.isNotBlank(str)) {
            try {
                return Enum.valueOf(clazz, str.trim());
            } catch (Exception ex) {
                log.error("Str 转 枚举类型方法失败, enumsClass:{}, str:{}", clazz, str);
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
        if (StringUtils.isBlank(str)) return null;
        try {
            return format.parse(str);
        } catch (Exception e) {
            log.error("Str 转 Date 方法失败, str:{}", str);
        }
        return null;
    }
}
