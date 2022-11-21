package com.example.good.impl;

import com.example.good.GoodService;
import com.example.good.dto.GoodCreateDTO;
import com.example.good.dto.GoodDTO;
import com.example.good.util.GoodUtils;
import com.example.good.util.PageInfoUtils;
import com.example.condition.GoodCondition;
import com.example.dao.GoodDao;
import com.example.model.GoodDO;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @title: 商品操作实现类
 * @author: vegetableOnlyBecause
 * @date 2022/11/10 17:41
 * @description:
 */
@Service
public class GoodServiceImpl implements GoodService {

    @Resource
    private GoodDao goodDao;

    @Override
    public GoodDTO getById(Integer id) {
        GoodDO good = goodDao.getById(id);
        return GoodUtils.do2dto(good);
    }

    @Override
    public PageInfo<GoodDTO> listInfo(GoodCondition condition) {
        PageInfo<GoodDO> dos = goodDao.listInfo(condition);
        return PageInfoUtils.pageInfoTrans(dos, GoodUtils::do2dto);
    }

    @Override
    public Integer save(GoodCreateDTO dto) {
        GoodDO good = GoodUtils.dto2do(dto);
        goodDao.save(good);
        return good.getId();
    }
}
