package com.example.common.utils;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
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
        if (null == model) {
            return null;
        }
        BeanUtils.copyProperties(model, result);
        return result;
    }

    public static <T, U> U model2Model(T model, U value, Consumer<U> consumer){
        U result = copyModel2Model(model, value);
        if (null != result && null != consumer) {
            consumer.accept(result);
        }
        return result;
    }

    public static <T, U> List<U> models2Models(List<T> models, Function<T, U> function) {
        if (CollectionUtils.isEmpty(models)) {
            return Collections.emptyList();
        }
        List<U> result = new ArrayList<>();
        for (T model : models) {
            U value = function.apply(model);
            if (null != value) {
                result.add(value);
            }
        }
        return result;
    }

    public static <T> void checkAndDeal(T model, Consumer<T> consumer) throws Exception {
        T oprModel = checkModel(model);
        consumer.accept(oprModel);
    }

    public static <T> T checkModel(T model) throws Exception {
        return Optional.ofNullable(model).orElseThrow(() -> new Exception("信息不存在:" + model.getClass()));
    }

}
