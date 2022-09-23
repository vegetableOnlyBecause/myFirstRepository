package com.example;

import com.example.dao.CupDao;
import com.example.model.Cup;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @title:
 * @author: vegetableOnlyBecause
 * @date 2022/9/23 16:27
 * @description:
 */
@Service("cupService")
public class CupService {

    @Resource
    private CupDao cupDao;

    public PageInfo<Cup> listCupByAddr(String addr, int page, int pageSize){
        return cupDao.listCupByAddr(addr, page, pageSize);
    }
}
