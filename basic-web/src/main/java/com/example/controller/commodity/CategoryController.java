package com.example.controller.commodity;

import com.alibaba.fastjson.JSON;
import com.example.commodity.CategoryService;
import com.example.commodity.dto.CategoryDTO;
import com.example.commodity.dto.CommodityDTO;
import com.example.commodity.util.CategoryUtils;
import com.example.commodity.util.PageInfoUtils;
import com.example.condition.CategoryCondition;
import com.example.controller.commodity.util.CategoryTransUtils;
import com.example.controller.commodity.util.CommodityTransUtils;
import com.example.controller.commodity.vo.CategoryCreate;
import com.example.controller.commodity.vo.CommodityCreate;
import com.example.response.OperationResult;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @title:
 * @author: vegetableOnlyBecause
 * @date 2022/11/11 9:29
 * @description:
 */
@Slf4j
@RestController
@RequestMapping(value = "/v1/category")
public class CategoryController {
    @Resource
    private CategoryService categoryService;

    @RequestMapping(value="/create", method = RequestMethod.POST)
    public OperationResult<Object> create(@Validated @RequestBody CategoryCreate create) {
        return OperationResult.succ(categoryService.save(CategoryTransUtils.vo2dto(create)));
    }

    @RequestMapping(method = RequestMethod.GET)
    public OperationResult<Object> listInfo(@RequestParam Map<String, String> param) {
        CategoryCondition condition = JSON.parseObject(JSON.toJSONString(param), CategoryCondition.class);
        if (null == condition) {
            condition = new CategoryCondition();
        }
        PageInfo<CategoryDTO> dtos = categoryService.listInfo(condition);
        return OperationResult.succ(
                PageInfoUtils.pageInfoTrans(dtos, CategoryTransUtils::dto2vo));
    }
}
