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

    /**
     * 创建用户
     * @param create 创建用户所需数据
     * @return 用户id
     */
    Integer save(UserOprParamDTO create);

    /**
     * 根据用户Id删除用户
     * @param userId 用户Id
     */
    void delUserById(Integer userId);

    /**
     * 更新用户信息
     * @param param 更新参数
     * @return 是否更新成功
     */
    boolean update(UserOprParamDTO param);

    /**
     * 根据用户Id查询用户信息
     * @param userId 用户id
     * @return 用户DTO
     */
    UserDTO getUserById(Integer userId);

    /**
     * 根据用户Name查询用户信息
     * @param userName 用户名
     * @return 用户DTO
     */
    UserDTO getUserByUserName(String userName);

    /**
     * 购买扣费
     * @param salerId 卖家ID
     * @param buyerId 买家ID
     * @param rmb 钱数
     * @throws Exception 钱不够的时候抛错
     */
    void operateMoney(Integer salerId, Integer buyerId, float rmb) throws Exception;
}
