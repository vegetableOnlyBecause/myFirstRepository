package com.example.dao;

import com.example.condition.CommodityCondition;
import com.example.mapper.CommodityDOMapper;
import com.example.model.CommodityDO;
import com.example.model.CommodityDOExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @title: 商品表信息数据库操作类
 * @author: vegetableOnlyBecause
 * @date 2022/11/10 16:50
 * @description:
 */
@Repository
public class CommodityDao {

    @Resource
    private CommodityDOMapper commodityDOMapper;

    public CommodityDO getById(String commodityId) {
        CommodityDOExample example = new CommodityDOExample();
        CommodityDOExample.Criteria criteria = example.createCriteria();
        criteria.andCommodityIdEqualTo(commodityId);
        List<CommodityDO> dos = commodityDOMapper.selectByExample(example);
        return Optional.ofNullable(dos.get(0)).orElse(null);
    }

    public PageInfo<CommodityDO> listInfo(CommodityCondition condition) {
        CommodityDOExample example = new CommodityDOExample();
        CommodityDOExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(condition.getCommodityName())) {
            criteria.andCommodityNameLike("%" + condition.getCommodityName() + "%");
        }
        if (StringUtils.isNotBlank(condition.getCategoryId())) {
            criteria.andCategoryIdEqualTo(condition.getCategoryId());
        }
        example.setOrderByClause(condition.getSortField()
                + " " + condition.getSortType());
        condition.initPageInfo();
        PageHelper.startPage(condition.getPage(), condition.getPageSize());
        List<CommodityDO> dos = commodityDOMapper.selectByExample(example);
        return new PageInfo<>(Optional.ofNullable(dos).orElse(Collections.emptyList()));
    }

    public void save(CommodityDO commodityDO) {
        commodityDOMapper.insertSelective(commodityDO);
    }

    public void del(String commodityId) {
        CommodityDOExample example = new CommodityDOExample();
        CommodityDOExample.Criteria criteria = example.createCriteria();
        criteria.andCommodityIdEqualTo(commodityId);
        commodityDOMapper.deleteByExample(example);
    }

}
