package com.example.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.condition.GoodCondition;
import com.example.dao.GoodDao;
import com.example.mapper.GoodDOMapper;
import com.example.model.GoodDO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

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
public class GoodDaoImpl extends ServiceImpl<GoodDOMapper, GoodDO> implements GoodDao {

    public List<GoodDO> listByType(int typeId) {
        List<GoodDO> dos = this.baseMapper.selectList(new LambdaQueryWrapper<GoodDO>()
                .eq(GoodDO::getTypeId, typeId));
        return CollectionUtils.isNotEmpty(dos) ? dos : Collections.emptyList();
    }

    public PageInfo<GoodDO> listInfo(GoodCondition condition) {
        LambdaQueryWrapper<GoodDO> wrapper = new LambdaQueryWrapper<>();
        // 设置查询条件
        Optional.ofNullable(condition.getUserId()).ifPresent(userId -> wrapper.eq(GoodDO::getUserId, userId));
        Optional.ofNullable(condition.getTypeId()).ifPresent(typeId -> wrapper.eq(GoodDO::getTypeId, typeId));
        if (StringUtils.isNotBlank(condition.getName())) {
            wrapper.like(GoodDO::getName, condition.getName());
        }
        condition.initPageInfo();
        PageHelper.startPage(condition.getPage(), condition.getPageSize());
        List<GoodDO> dos = this.baseMapper.selectList(wrapper);
        return new PageInfo<>(CollectionUtils.isNotEmpty(dos) ? dos : Collections.emptyList());
    }

}
