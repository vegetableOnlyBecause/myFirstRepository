package com.example.user.impl;

import com.example.aop.CacheAop;
import com.example.aop.CacheAopEnums;
import com.example.dao.CommonUserDao;
import com.example.model.CommonUserDO;
import com.example.user.UserService;
import com.example.user.dto.UserCreateDTO;
import com.example.user.dto.UserDTO;
import com.example.user.util.UserUtils;
import com.github.pagehelper.PageInfo;
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

    @Override
    public void saveUserInfo(UserCreateDTO create) {
        CommonUserDO userDO = UserUtils.dto2do(create);
        if (null == userDO){
            // 抛错;
        }
        commonUserDao.saveUserInfo(userDO);
    }

    @Override
    @CacheAop(operateEnums = CacheAopEnums.GET_USER_BY_ID)
    public UserDTO getUserById(String userId) {
        CommonUserDO userDO = commonUserDao.getUserById(userId);
        return UserUtils.do2Dto(userDO);
    }

    public PageInfo<UserDTO> listUserByConditions(Map<String, Object> condition, int page, int pageSize){
        PageInfo<CommonUserDO> doPage = commonUserDao.listUserByConditions(condition, page, pageSize);
        List<CommonUserDO> dos = doPage.getList();
        List<UserDTO> dtos = UserUtils.dos2Dtos(dos);
        return new PageInfo<>(dtos);
    }
}
