package com.example.good;

import com.example.good.dto.TypeCreateDTO;
import com.example.good.dto.TypeDTO;
import com.example.condition.TypeCondition;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @title: 商品类目操作
 * @author: vegetableOnlyBecause
 * @date 2022/11/10 17:40
 * @description:
 */
public interface TypeService {

    boolean save(TypeCreateDTO create);

    PageInfo<TypeDTO> listInfo(TypeCondition condition);

    TypeDTO getByName(String name);

    List<TypeDTO> all();

    int countById(String typeName);
}
