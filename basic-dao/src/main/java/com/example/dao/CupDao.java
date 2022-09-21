package com.example.dao;

import com.example.mapper.CupMapper;
import com.example.model.Cup;
import com.example.model.CupExample;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

@Service("cupDao")
public class CupDao {

    @Resource
    private CupMapper cupMapper;

    public Cup getCup(String id){
        CupExample example = new CupExample();
        CupExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id);
        List<Cup> cups = cupMapper.selectByExample(example);
        return CollectionUtils.isEmpty(cups) ? null : cups.get(0);
    }
}
