package com.example.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.model.UserDO;

/**
 * @title:
 * @author: vegetableOnlyBecause
 * @date 2023/8/8 17:08
 * @description:
 */
public interface UserDao extends IService<UserDO> {

    void update(UserDO user);

    UserDO getUserByName(String userName);
}
