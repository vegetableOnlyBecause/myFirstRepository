package com.example.controller.commodity;

import com.example.commodity.CommodityService;
import com.example.commodity.dto.CommodityDTO;
import com.example.controller.commodity.util.CommodityTransUtils;
import com.example.controller.commodity.vo.CommodityCreate;
import com.example.response.OperationResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @title: 商品Controller
 * @author: vegetableOnlyBecause
 * @date 2022/11/10 18:56
 * @description:
 */
@Slf4j
@RestController
@RequestMapping(value = "/v1/commodity")
public class CommodityController {

    @Resource
    private CommodityService commodityService;

    @RequestMapping(value="/create", method = RequestMethod.POST)
    public OperationResult<Object> create(@Validated @RequestBody CommodityCreate create) {
        return OperationResult.succ(commodityService.save(CommodityTransUtils.vo2dto(create)));
    }

    @RequestMapping(value="/{categoryId}", method = RequestMethod.GET)
    public OperationResult<Object> create(@Validated @PathVariable(name = "categoryId") String categoryId) {
        List<CommodityDTO> dtos = commodityService.listByCategoryId(categoryId);
        return OperationResult.succ(CommodityTransUtils.dtos2vos(dtos));
    }
}
