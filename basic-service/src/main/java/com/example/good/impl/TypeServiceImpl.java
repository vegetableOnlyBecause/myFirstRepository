package com.example.good.impl;

import com.example.common.utils.OprUtils;
import com.example.dao.GoodDao;
import com.example.good.TypeService;
import com.example.good.dto.TypeCreateDTO;
import com.example.good.dto.TypeDTO;
import com.example.good.util.TypeUtils;
import com.example.good.util.PageInfoUtils;
import com.example.condition.TypeCondition;
import com.example.dao.TypeDao;
import com.example.model.TypeDO;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @title: 类目服务类
 * @author: vegetableOnlyBecause
 * @date 2022/11/11 9:01
 * @description:
 */
@Service("categoryService")
public class TypeServiceImpl implements TypeService {

    @Resource
    private TypeDao typeDao;
    @Resource
    private GoodDao goodDao;

    @Override
    public Integer save(TypeCreateDTO create) {
        return typeDao.save(TypeUtils.dto2do(create));
    }

    @Override
    public PageInfo<TypeDTO> listInfo(TypeCondition condition) {
        PageInfo<TypeDO> result = typeDao.listInfo(condition);
        return PageInfoUtils.pageInfoTrans(result, TypeUtils::do2dto);
    }

    @Override
    public TypeDTO getByName(String name) {
        TypeDO type = typeDao.getByName(name);
        return TypeUtils.do2dto(type);
    }

    @Override
    public List<TypeDTO> all() {
        return OprUtils.models2Models(typeDao.all(), TypeUtils::do2dto);
    }

    @Override
    public int countById(String typeName) {
        TypeDO type = typeDao.getByName(typeName);
        if (null == type) {
            return 0;
        }
        return goodDao.listByType(type.getId()).size();
    }
}
