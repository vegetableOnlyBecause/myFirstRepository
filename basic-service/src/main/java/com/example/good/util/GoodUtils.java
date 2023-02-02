package com.example.good.util;

import com.example.common.utils.OprUtils;
import com.example.good.dto.GoodDTO;
import com.example.good.dto.GoodOprDTO;
import com.example.model.GoodDO;
import com.example.user.util.UserUtils;

import java.util.function.Consumer;

/**
 * @title: 商品相关工具类
 * @author: vegetableOnlyBecause
 * @date 2022/11/10 17:48
 * @description:
 */
public class GoodUtils {

    public static GoodDTO do2dto(GoodDO goodDO) {
        return OprUtils.copyModel2Model(goodDO, new GoodDTO());

    }

    public static GoodDO dto2do(GoodOprDTO dto) {
        Consumer<GoodDO> consumer = good -> {
            if (null == good.getId()) {
                good.setId(UserUtils.initId());
            }
        };
        return OprUtils.model2Model(dto, new GoodDO(), consumer);
    }
}
