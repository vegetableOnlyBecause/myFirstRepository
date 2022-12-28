package com.example.user;

import com.example.condition.UserCondition;
import com.example.user.dto.UserOprParamDTO;
import com.example.user.dto.UserDTO;
import com.github.pagehelper.PageInfo;

/**
 * @title: 用户信息查询接口
 * @author: vegetableOnlyBecause
 * @date 2022/9/26 14:46
 * @description:
 */
public interface UserService {

    Integer save(UserOprParamDTO create);

    /**
     * 根据用户Id删除用户
     * @param userId 用户Id
     */
    void delUserById(Integer userId);

    boolean update(UserOprParamDTO param) throws Exception;

    /**
     * 根据用户Id查询用户信息
     * @param userId 用户Id
     */
    UserDTO getUserById(Integer userId);

    UserDTO getUserByUserName(String userName);

    void operateMoney(Integer salerId, Integer buyerId, float rmb) throws Exception;

    /**
     * 批量查询用户信息
     * @param condition 查询条件
     * @return
     */
    PageInfo<UserDTO> listInfo(UserCondition condition);
}
