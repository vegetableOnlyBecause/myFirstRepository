package com.example.controller.good.util;

import com.example.common.utils.OprUtils;
import com.example.controller.good.vo.GoodCreate;
import com.example.controller.good.vo.GoodVO;
import com.example.good.dto.GoodDTO;
import com.example.good.dto.GoodOprDTO;

import java.util.function.Consumer;

/**
 * @title: 商品vo转换类
 * @author: vegetableOnlyBecause
 * @date 2022/11/10 19:09
 * @description:
 */
public class GoodTransUtils {

    public static GoodOprDTO vo2dto(GoodCreate create) {
        return OprUtils.copyModel2Model(create, new GoodOprDTO());
    }

    public static GoodVO dto2vo(GoodDTO dto) {
        Consumer<GoodVO> consumer = vo -> {
            vo.setEndTime(CommentTransUtils.date2String(dto.getEndTime()));
            vo.setCreateTime(CommentTransUtils.date2String(dto.getCreateTime()));
            vo.setUpdateTime(CommentTransUtils.date2String(dto.getUpdateTime()));
        };
        return OprUtils.model2Model(dto, new GoodVO(), consumer);
    }
}
