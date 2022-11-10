package com.example.commodity.impl;

import com.example.commodity.CommodityService;
import com.example.commodity.dto.CommodityCreateDTO;
import com.example.commodity.dto.CommodityDTO;
import com.example.commodity.util.CommodityUtils;
import com.example.dao.CommodityDao;
import com.example.model.CommodityDO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * @title: 商品操作实现类
 * @author: vegetableOnlyBecause
 * @date 2022/11/10 17:41
 * @description:
 */
@Service
public class CommodityServiceImpl implements CommodityService {

    @Resource
    private CommodityDao commodityDao;

    @Override
    public List<CommodityDTO> listByCategoryId(String categoryId) {
        if (StringUtils.isBlank(categoryId)) {
            return Collections.emptyList();
        }
        return CommodityUtils.dos2dtos(commodityDao.listByCategoryId(categoryId));
    }

    @Override
    public String save(CommodityCreateDTO dto) {
        CommodityDO commodityDO = CommodityUtils.dto2do(dto);
        commodityDao.save(commodityDO);
        return commodityDO.getCommodityId();
    }
}
