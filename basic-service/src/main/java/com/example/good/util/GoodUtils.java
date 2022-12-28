package com.example.good.util;

import com.example.good.dto.GoodOprDTO;
import com.example.good.dto.GoodDTO;
import com.example.model.GoodDO;
import com.example.user.util.UserUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @title: 商品相关工具类
 * @author: vegetableOnlyBecause
 * @date 2022/11/10 17:48
 * @description:
 */
public class GoodUtils {

    public static GoodDTO do2dto(GoodDO goodDO) {
        if (null == goodDO) {
            return null;
        }
        GoodDTO dto = new GoodDTO();
        BeanUtils.copyProperties(goodDO, dto);
        return dto;
    }

    public static GoodDO dto2do(GoodOprDTO dto) {
        if (null == dto) {
            return null;
        }
        GoodDO good = new GoodDO();
        BeanUtils.copyProperties(dto, good);
        if (null == good.getId()) {
            good.setId(UserUtils.initId());
        }
        return good;
    }
}
