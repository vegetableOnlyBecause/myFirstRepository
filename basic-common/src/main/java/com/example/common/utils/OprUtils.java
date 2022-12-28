package com.example.common.utils;

import org.apache.commons.collections4.CollectionUtils;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

/**
 * @title:
 * @author: vegetableOnlyBecause
 * @date 2022/12/12 14:39
 * @description:
 */
public class OprUtils {

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


}
