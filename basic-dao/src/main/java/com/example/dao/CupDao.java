package com.example.dao;

import com.example.cache.aop.CacheAop;
import com.example.cache.aop.CacheAopEnums;
import com.example.mapper.CupMapper;
import com.example.model.Cup;
import com.example.model.CupExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@Service("cupDao")
public class CupDao {
    @Resource
    private CupMapper cupMapper;

    @CacheAop(operateEnums = CacheAopEnums.GET_CUP_BY_ID, needLocalCache = true)
    public Cup getCupById(String id){
        CupExample example = new CupExample();
        CupExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id);
        List<Cup> infos = cupMapper.selectByExample(example);
        return CollectionUtils.isEmpty(infos) ? null : infos.get(0);
    }

    @CacheAop(operateEnums = CacheAopEnums.LIST_CUP_BY_NAME, needLocalCache = true)
    public List<Cup> listCupByName(String name){
        CupExample example = new CupExample();
        CupExample.Criteria criteria = example.createCriteria();
        criteria.andUserNameEqualTo(name);
        List<Cup> infos = cupMapper.selectByExample(example);
        return CollectionUtils.isEmpty(infos) ? Collections.EMPTY_LIST : infos;
    }

    public PageInfo<Cup> listCupByAddr(String addr, int page, int pageSize){
        CupExample example = new CupExample();
        CupExample.Criteria criteria = example.createCriteria();
        criteria.andAddressEqualTo(addr);
        PageHelper.startPage(page, pageSize);
        List<Cup> infos = cupMapper.selectByExample(example);
        return new PageInfo<>(infos);
    }
}
