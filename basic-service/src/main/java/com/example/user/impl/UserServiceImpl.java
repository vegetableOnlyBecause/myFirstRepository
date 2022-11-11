package com.example.user.impl;

import com.example.aop.CacheAop;
import com.example.aop.CacheAopEnums;
import com.example.common.redis.RedisOperator;
import com.example.condition.UserCondition;
import com.example.dao.CommonUserDao;
import com.example.model.CommonUserDO;
import com.example.user.UserService;
import com.example.user.dto.UserCreateDTO;
import com.example.user.dto.UserDTO;
import com.example.user.util.UserUtils;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @title: 用户信息查询实现类
 * @author: vegetableOnlyBecause
 * @date 2022/9/26 15:11
 * @description:
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private CommonUserDao commonUserDao;
    @Resource
    private RedisOperator redisOperator;
    private static final String symbol = "#";

    @Override
    public void save(UserCreateDTO create) {
        CommonUserDO userDO = UserUtils.dto2do(create);
        if (null == userDO){
            // 抛错;
        }
        commonUserDao.save(userDO);
    }

    @Override
    public void delUserById(String userId) {
        if (StringUtils.isBlank(userId)) {
            //抛错
        }
        commonUserDao.del(userId);
        redisOperator.del(CacheAopEnums.GET_USER_BY_ID + symbol + userId);
    }

    @Override
    @CacheAop(operateEnums = CacheAopEnums.GET_USER_BY_ID)
    public UserDTO getUserById(String userId) {
        CommonUserDO userDO = commonUserDao.getUserById(userId);
        return UserUtils.do2Dto(userDO);
    }

    @Override
    public PageInfo<UserDTO> listInfo(UserCondition condition){
        PageInfo<CommonUserDO> doPage = commonUserDao.listInfo(condition);
        List<CommonUserDO> dos = doPage.getList();
        List<UserDTO> dtos = UserUtils.dos2Dtos(dos);
        return new PageInfo<>(dtos);
    }
}
