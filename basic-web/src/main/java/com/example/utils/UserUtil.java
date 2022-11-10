package com.example.utils;

import com.example.controller.user.vo.UserCreate;
import com.example.controller.user.vo.UserVO;
import com.example.user.dto.UserCreateDTO;
import com.example.user.dto.UserDTO;

/**
 * @title:
 * @author: vegetableOnlyBecause
 * @date 2022/9/26 16:10
 * @description:
 */
public class UserUtil {
    public static UserVO dto2vo(UserDTO dto){
        if (null == dto){
            return null;
        }
        UserVO vo = new UserVO();
        vo.setUserId(dto.getUserId());
        vo.setUserName(dto.getUserName());
        vo.setNickName(dto.getNickName());
        vo.setPhoneNumber(dto.getPhoneNumber());
        vo.setBirthday(dto.getBirthday());
        vo.setEmail(dto.getEmail());
        vo.setRegisterTime(dto.getRegisterTime());
        return vo;
    }

    public static UserCreateDTO vo2dto(UserCreate vo){
        if (null == vo) return null;
        UserCreateDTO dto = new UserCreateDTO();
        dto.setUserName(vo.getUserName());
        dto.setPassword(vo.getPassword());
        dto.setNickName(vo.getNickName());
        dto.setPhoneNumber(vo.getPhoneNumber());
        dto.setBirthday(vo.getBirthday());
        dto.setEmail(vo.getEmail());
        return dto;
    }
}
