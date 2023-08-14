package com.example.controller.good;

import com.alibaba.fastjson.JSON;
import com.example.common.utils.OprUtils;
import com.example.controller.good.vo.TypeVO;
import com.example.good.TypeService;
import com.example.good.dto.TypeDTO;
import com.example.good.util.PageInfoUtils;
import com.example.condition.TypeCondition;
import com.example.controller.good.util.TypeTransUtils;
import com.example.controller.good.vo.TypeCreate;
import com.example.response.OperationResult;
import com.example.response.Result;
import com.example.response.ResultEnum;
import com.example.response.ResultUtil;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @title:
 * @author: vegetableOnlyBecause
 * @date 2022/11/11 9:29
 * @description:
 */
@Slf4j
@RestController
@RequestMapping(value = "/market")
public class TypeController {
    @Resource
    private TypeService typeService;

    @RequestMapping(value="/create", method = RequestMethod.POST)
    public OperationResult<Object> create(@Validated @RequestBody TypeCreate param) {
        return OperationResult.succ(typeService.save(TypeTransUtils.vo2dto(param)));
    }

    @RequestMapping(method = RequestMethod.GET)
    public OperationResult<Object> listInfo(@RequestParam Map<String, String> param) {
        TypeCondition condition = JSON.parseObject(JSON.toJSONString(param), TypeCondition.class);
        if (null == condition) {
            condition = new TypeCondition();
        }
        PageInfo<TypeDTO> dtos = typeService.listInfo(condition);
        return OperationResult.succ(
                PageInfoUtils.pageInfoTrans(dtos, TypeTransUtils::dto2vo));
    }

    @GetMapping(value = "/types/name")
    public Result<List<String>> getAllTypesName(){
        List<TypeDTO> all = typeService.all();
        List<String> allTypeNames = all.stream().map(TypeDTO::getName).collect(Collectors.toList());
        return ResultUtil.success(ResultEnum.GET_TYPES, allTypeNames);
    }

    @GetMapping(value = "/types")
    public Result<List<TypeVO>> getAllTypes(){
        List<TypeDTO> all = typeService.all();
        return ResultUtil.success(ResultEnum.GET_TYPES, OprUtils.models2Models(all, TypeTransUtils::dto2vo));
    }

    @GetMapping(value = "/count/{type}")
    public Result<Integer> getGoodsCountByType(@PathVariable("type") String typeName){
        Integer count = typeService.countById(typeName);
        return ResultUtil.success(ResultEnum.GET_GOODS_COUNT, count);
    }
}
