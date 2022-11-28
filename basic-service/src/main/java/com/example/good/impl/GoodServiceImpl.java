package com.example.good.impl;

import com.example.dao.TypeDao;
import com.example.good.GoodService;
import com.example.good.dto.GoodOprDTO;
import com.example.good.dto.GoodDTO;
import com.example.good.util.GoodUtils;
import com.example.good.util.PageInfoUtils;
import com.example.condition.GoodCondition;
import com.example.dao.GoodDao;
import com.example.model.GoodDO;
import com.example.model.TypeDO;
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
    @Resource
    private TypeDao typeDao;

    @Override
    public GoodDTO getById(Integer id) {
        GoodDO good = goodDao.getById(id);
        return GoodUtils.do2dto(good);
    }

    @Override
    public void delById(Integer id) {
        goodDao.del(id);
    }

    @Override
    public PageInfo<GoodDTO> listInfo(GoodCondition condition) {
        PageInfo<GoodDO> dos = goodDao.listInfo(condition);
        return PageInfoUtils.pageInfoTrans(dos, GoodUtils::do2dto);
    }

    @Override
    public Integer save(GoodOprDTO dto) {
        GoodDO good = GoodUtils.dto2do(dto);
        goodDao.save(good);
        TypeDO type = typeDao.getById(dto.getTypeId());
        Integer number = type.getNumber();
        type.setNumber(number + 1);
        typeDao.update(type);
        return good.getId();
    }

    @Override
    public void update(GoodOprDTO dto) {
        GoodDO good = GoodUtils.dto2do(dto);
        goodDao.update(good);
    }
}
