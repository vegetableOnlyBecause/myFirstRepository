package com.example.common.utils;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @title:
 * @author: vegetableOnlyBecause
 * @date 2022/12/12 14:39
 * @description:
 */
public class OprUtils {

    public static <T, U> U copyModel2Model(T model, U result){
        Objects.requireNonNull(model);
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
}
