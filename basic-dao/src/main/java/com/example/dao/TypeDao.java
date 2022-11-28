package com.example.dao;

import com.example.condition.TypeCondition;
import com.example.mapper.TypeDOMapper;
import com.example.model.TypeDO;
import com.example.model.TypeDOExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @title: 商品类目数据库操作类
 * @author: vegetableOnlyBecause
 * @date 2022/11/10 17:05
 * @description:
 */
@Repository
public class TypeDao {

    @Resource
    private TypeDOMapper typeDOMapper;

    public TypeDO getById(int id) {
        TypeDOExample example = new TypeDOExample();
        TypeDOExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id);
        List<TypeDO> dos = typeDOMapper.selectByExample(example);
        return CollectionUtils.isNotEmpty(dos) ? dos.get(0) : null;
    }

    public TypeDO getByName(String name) {
        TypeDOExample example = new TypeDOExample();
        TypeDOExample.Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(name);
        List<TypeDO> dos = typeDOMapper.selectByExample(example);
        return CollectionUtils.isNotEmpty(dos) ? dos.get(0) : null;
    }

    public PageInfo<TypeDO> listInfo(TypeCondition condition) {
        TypeDOExample example = new TypeDOExample();
        TypeDOExample.Criteria criteria = example.createCriteria();


        example.setOrderByClause(condition.getSortField()
                + " " + condition.getSortType());
        condition.initPageInfo();
        PageHelper.startPage(condition.getPage(), condition.getPageSize());
        List<TypeDO> dos = typeDOMapper.selectByExample(example);
        return new PageInfo<>(Optional.ofNullable(dos).orElse(Collections.emptyList()));
    }

    public List<TypeDO> all(){
        TypeDOExample example = new TypeDOExample();
        List<TypeDO> dos = typeDOMapper.selectByExample(example);
        return CollectionUtils.isNotEmpty(dos) ? dos : Collections.emptyList();
    }

    public Integer save(TypeDO typeDO) {
        typeDOMapper.insertSelective(typeDO);
        return typeDO.getId();
    }

    public void update(TypeDO typeDO) {
        typeDOMapper.updateByPrimaryKeySelective(typeDO);
    }

    public void delById(Integer id) {
        typeDOMapper.deleteByPrimaryKey(id);
    }

}
