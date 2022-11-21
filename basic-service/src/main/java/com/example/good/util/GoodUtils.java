package com.example.good.util;

import com.example.good.dto.GoodCreateDTO;
import com.example.good.dto.GoodDTO;
import com.example.model.GoodDO;
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

    public static List<GoodDTO> dos2dtos(List<GoodDO> dos) {
        if (CollectionUtils.isEmpty(dos)) {
            return Collections.emptyList();
        }
        List<GoodDTO> dtos = new ArrayList<>();
        for (GoodDO goodDO : dos) {
            GoodDTO dto = do2dto(goodDO);
            if (null != dto) {
                dtos.add(dto);
            }
        }
        return dtos;
    }

    public static GoodDO dto2do(GoodCreateDTO dto) {
        if (null == dto) {
            return null;
        }
        GoodDO good = new GoodDO();
        BeanUtils.copyProperties(dto, good);
        int id = (int) ((Math.random() * 9 + 1) * Math.pow(10, 10));
        good.setId(id);
        return good;
    }
}
