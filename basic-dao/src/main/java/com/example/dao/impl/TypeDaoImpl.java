package com.example.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.condition.TypeCondition;
import com.example.dao.TypeDao;
import com.example.mapper.TypeDOMapper;
import com.example.model.TypeDO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * @title: 商品类目数据库操作类
 * @author: vegetableOnlyBecause
 * @date 2022/11/10 17:05
 * @description:
 */
@Repository
public class TypeDaoImpl extends ServiceImpl<TypeDOMapper, TypeDO> implements TypeDao {

    public TypeDO getByName(String name) {
        return baseMapper.selectOne(new LambdaQueryWrapper<TypeDO>().eq(TypeDO::getName, name));
    }

    public PageInfo<TypeDO> listInfo(TypeCondition condition) {
        LambdaQueryWrapper<TypeDO> wrapper= new LambdaQueryWrapper<>();

        PageHelper.startPage(condition.getPage(), condition.getPageSize());
        List<TypeDO> dos = baseMapper.selectList(wrapper);
        return new PageInfo<>(CollectionUtils.isNotEmpty(dos) ? dos : Collections.emptyList());
    }

    public List<TypeDO> all(){
        List<TypeDO> dos = baseMapper.selectList(new LambdaQueryWrapper<TypeDO>());
        return CollectionUtils.isNotEmpty(dos) ? dos : Collections.emptyList();
    }
}
