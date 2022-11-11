package com.example.commodity.util;

import com.example.commodity.dto.CategoryCreateDTO;
import com.example.commodity.dto.CategoryDTO;
import com.example.model.CategoryDO;
import com.github.pagehelper.PageInfo;
import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @title:
 * @author: vegetableOnlyBecause
 * @date 2022/11/11 9:22
 * @description:
 */
public class CategoryUtils {

    public static CategoryDTO do2dto(CategoryDO category) {
        if (null == category) {
            return null;
        }
        CategoryDTO dto = new CategoryDTO();
        BeanUtils.copyProperties(category, dto);
        return dto;
    }

    public static List<CategoryDTO> dos2dtos(List<CategoryDO> dos) {
        if (CollectionUtils.isEmpty(dos)) {
            return Collections.emptyList();
        }
        List<CategoryDTO> dtos = new ArrayList<>();
        for (CategoryDO category : dos) {
            CategoryDTO dto = do2dto(category);
            if (null != dto) {
                dtos.add(dto);
            }
        }
        return dtos;
    }



    public static CategoryDO dto2do(CategoryCreateDTO dto) {
        if (null == dto) {
            return null;
        }
        CategoryDO category = new CategoryDO();
        BeanUtils.copyProperties(dto, category);
        return category;
    }
}
