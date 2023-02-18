package com.example.good.impl;

import com.example.common.utils.OprUtils;
import com.example.condition.GoodCondition;
import com.example.dao.GoodDao;
import com.example.dao.TypeDao;
import com.example.good.GoodService;
import com.example.good.dto.GoodDTO;
import com.example.good.dto.GoodOprDTO;
import com.example.good.util.GoodUtils;
import com.example.good.util.PageInfoUtils;
import com.example.model.GoodDO;
import com.example.model.TypeDO;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @title: 商品操作实现类
 * @author: vegetableOnlyBecause
 * @date 2022/11/10 17:41
 * @description:
 */
@Service
@Slf4j
public class GoodServiceImpl implements GoodService {

    @Resource
    private GoodDao goodDao;
    @Resource
    private TypeDao typeDao;
    // todo: 改地址
    private static String save_path = "D:\\IdeaProjects\\MyFirstVue\\market-front\\src\\dist\\";
    private static String upload_path = "D:\\图片\\";

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
        List<GoodDO> goods = dos.getList().stream()
                .filter(good ->  1 == good.getStatus()
                        && good.getNum() > 0
                        && new Date().before(good.getEndTime()))
                .collect(Collectors.toList());
        dos.setList(goods);
        return PageInfoUtils.pageInfoTrans(dos, GoodUtils::do2dto);
    }

    @Override
    public Integer save(GoodOprDTO dto) throws Exception {
        GoodDO good = GoodUtils.dto2do(dto);
        goodDao.save(good);
        TypeDO type = typeDao.getById(dto.getTypeId());
        Integer number = type.getNumber();
        type.setNumber(number + 1);
        typeDao.update(type);
        if (StringUtils.isNotBlank(dto.getImgUrl())) {
            savePic(dto.getImgUrl());
        }
        return good.getId();
    }

    @Override
    public void update(GoodOprDTO dto) {
        GoodDO good = GoodUtils.dto2do(dto);
        goodDao.update(good);
    }

    @Override
    public void lessInventory(Integer goodId, Integer num) throws Exception {
        GoodDO good = goodDao.getById(goodId);
        Objects.requireNonNull(good, "商品信息不存在:" + goodId);
        int inventory = good.getNum() - num;
        if (inventory < 0) {
            throw new Exception("库存不够了");
        }
        good.setNum(inventory);
        goodDao.update(good);
    }

    private void savePic(String picName) throws Exception {
        String savePath = save_path + picName;
        String uploadPath = upload_path + picName;
        InputStream input = new FileInputStream(uploadPath);
        FileOutputStream output = new FileOutputStream(savePath);
        FileCopyUtils.copy(input, output);
    }
}
