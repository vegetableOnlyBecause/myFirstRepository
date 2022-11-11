package com.example.controller.commodity.util;

import com.example.commodity.dto.CategoryCreateDTO;
import com.example.commodity.dto.CategoryDTO;
import com.example.controller.commodity.vo.CategoryCreate;
import com.example.controller.commodity.vo.CategoryVO;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections4.CollectionUtils;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @title:
 * @author: vegetableOnlyBecause
 * @date 2022/11/11 9:34
 * @description:
 */
public class CategoryTransUtils {

    public static CategoryVO dto2vo(CategoryDTO dto) {
        if (null == dto) {
            return null;
        }
        CategoryVO vo = new CategoryVO();
        BeanUtils.copyProperties(dto, vo);
        return vo;
    }

    public static CategoryCreateDTO vo2dto(CategoryCreate create) {
        if (null == create) {
            return null;
        }
        CategoryCreateDTO dto = new CategoryCreateDTO();
        BeanUtils.copyProperties(create, dto);
        return dto;
    }
}
