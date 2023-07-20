package com.example.user.impl;

import com.example.aop.CacheAop;
import com.example.aop.CacheAopEnums;
import com.example.common.redis.RedisOperator;
import com.example.common.utils.OprUtils;
import com.example.condition.UserCondition;
import com.example.dao.UserDao;
import com.example.model.UserDO;
import com.example.user.UserService;
import com.example.user.dto.UserDTO;
import com.example.user.dto.UserOprParamDTO;
import com.example.user.util.UserUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @title: 用户信息查询实现类
 * @author: vegetableOnlyBecause
 * @date 2022/9/26 15:11
 * @description:
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;
    @Resource
    private RedisOperator redisOperator;
    private static final String symbol = "#";

    @Override
    public Integer save(UserOprParamDTO create) {
        UserDO userDO = UserUtils.dto2do(create);
        userDao.save(userDO);
        return userDO.getId();
    }

    @Override
    public void delUserById(Integer userId) {
        if (null == userId) {
            //抛错
        }
        userDao.del(userId);
        redisOperator.del(CacheAopEnums.GET_USER_BY_ID + symbol + userId);
    }

    @Override
    public boolean update(UserOprParamDTO param) {
        UserDO user = userDao.getById(param.getId());
        Objects.requireNonNull(user, "用户信息不存在:" + param.getId());
        userDao.update(UserUtils.dto2do(param));
        return true;
    }

    @Override
    @CacheAop(type = CacheAopEnums.GET_USER_BY_ID)
    public UserDTO getUserById(Integer userId) {
        UserDO userDO = userDao.getById(userId);
        return UserUtils.do2Dto(userDO);
    }

    @Override
    public UserDTO getUserByUserName(String userName) {
        UserDO user = userDao.getUserByName(userName);
        return UserUtils.do2Dto(user);
    }

    @Override
    public void operateMoney(Integer salerId, Integer buyerId, float rmb) throws Exception {
        UserDO saler = userDao.getById(salerId);
        Float salerCoin = saler.getCoin();
        saler.setCoin(salerCoin + rmb);
        UserDO buyer = userDao.getById(buyerId);
        float coin = buyer.getCoin() - rmb;
        if (coin < 0) {
            throw new Exception("钱不够啊,铁子");
        }
        buyer.setCoin(coin);
        userDao.update(saler);
        userDao.update(buyer);
    }

    @Override
    public PageInfo<UserDTO> listInfo(UserCondition condition){
        PageInfo<UserDO> doPage = userDao.listInfo(condition);
        List<UserDO> dos = doPage.getList();
        List<UserDTO> dtos = OprUtils.models2Models(dos, UserUtils::do2Dto);
        return new PageInfo<>(dtos);
    }
}
