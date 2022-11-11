package com.example.user;

import com.example.user.dto.UserCreateDTO;
import com.example.user.dto.UserDTO;
import com.github.pagehelper.PageInfo;

import java.io.IOException;
import java.util.Map;

/**
 * @title: 用户信息查询接口
 * @author: vegetableOnlyBecause
 * @date 2022/9/26 14:46
 * @description:
 */
public interface UserService {

    void save(UserCreateDTO create);

    /**
     * 根据用户Id查询用户信息
     * @param userId 用户Id
     */
    UserDTO getUserById(String userId);

    /**
     * 根据用户Id删除用户
     * @param userId 用户Id
     */
    void delUserById(String userId);

    /**
     * 批量查询用户信息
     * @param condition 查询条件
     * @param page 页数
     * @param pageSize 页面大小
     * @return
     */
    PageInfo<UserDTO> listUserByConditions(Map<String, String> condition, int page, int pageSize);
}
