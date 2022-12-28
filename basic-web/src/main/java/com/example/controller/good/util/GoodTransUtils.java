package com.example.controller.good.util;

import com.example.good.dto.GoodOprDTO;
import com.example.good.dto.GoodDTO;
import com.example.controller.good.vo.GoodCreate;
import com.example.controller.good.vo.GoodVO;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @title: 商品vo转换类
 * @author: vegetableOnlyBecause
 * @date 2022/11/10 19:09
 * @description:
 */
public class GoodTransUtils {

    public static GoodOprDTO vo2dto(GoodCreate create) {
        if (null == create) {
            return null;
        }
        GoodOprDTO dto = new GoodOprDTO();
        BeanUtils.copyProperties(create, dto);
        return dto;
    }

    public static GoodVO dto2vo(GoodDTO dto) {
        if (null == dto) {
            return null;
        }
        GoodVO vo = new GoodVO();
        BeanUtils.copyProperties(dto, vo);
        vo.setEndTime(CommentTransUtils.date2String(dto.getEndTime()));
        vo.setCreateTime(CommentTransUtils.date2String(dto.getCreateTime()));
        vo.setUpdateTime(CommentTransUtils.date2String(dto.getUpdateTime()));
        return vo;
    }
}
