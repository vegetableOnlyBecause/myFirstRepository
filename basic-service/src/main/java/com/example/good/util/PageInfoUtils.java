package com.example.good.util;

import com.github.pagehelper.PageInfo;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @title: Page转换方法
 * @author: vegetableOnlyBecause
 * @date 2022/11/11 14:04
 * @description:
 */
public class PageInfoUtils {

    public static <T, R> PageInfo<R> pageInfoTrans(PageInfo<T> page, Function<T,R> function) {
        PageInfo<R> result = new PageInfo<>();
        if (null == page || CollectionUtils.isEmpty(page.getList())) {
            return result;
        }
        BeanUtils.copyProperties(page, result);
        Optional.ofNullable(function).ifPresent(f ->
                result.setList(page.getList().stream().map(function).collect(Collectors.toList())));
        return result;
    }
}
