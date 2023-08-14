package com.example.user.impl;

import com.example.common.constant.RedisKeyConstants;
import com.example.common.utils.RedisUtils;
import com.example.dao.UserDao;
import com.example.model.UserDO;
import com.example.user.UserService;
import com.example.user.dto.UserDTO;
import com.example.user.dto.UserOprParamDTO;
import com.example.user.util.UserUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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

    @Override
    @Transactional
    public Integer save(UserOprParamDTO create) {
        UserDO userDO = UserUtils.dto2do(create);
        userDao.save(userDO);
        return userDO.getId();
    }

    @Override
    public void delUserById(Integer userId) {
        Objects.requireNonNull(userId, "用户id为空");
        userDao.removeById(userId);
        RedisUtils.del(RedisKeyConstants.USER + userId);
    }

    @Override
    public boolean update(UserOprParamDTO param) {
        UserDO user = userDao.getById(param.getId());
        Objects.requireNonNull(user, "用户信息不存在:" + param.getId());
        userDao.update(UserUtils.dto2do(param));
        return true;
    }

    @Override
//    @CacheAop(type = CacheAopEnums.GET_USER_BY_ID)
    public UserDTO getUserById(Integer userId) {
        String key = RedisKeyConstants.USER + userId;
        return RedisUtils.get(key, UserDTO.class, RedisKeyConstants.ONE_DAY,
                () -> UserUtils.do2Dto(userDao.getById(userId)));
    }

    @Override
    public UserDTO getUserByUserName(String userName) {
        String key = RedisKeyConstants.USER + userName;
        return RedisUtils.get(key, UserDTO.class, RedisKeyConstants.ONE_DAY,
                () -> UserUtils.do2Dto(userDao.getUserByName(userName)));
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
}
