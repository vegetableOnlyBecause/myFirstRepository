package com.example.controller.user.util;

import com.example.controller.user.vo.UserCreate;
import com.example.controller.user.vo.UserVO;
import com.example.user.dto.UserCreateDTO;
import com.example.user.dto.UserDTO;
import org.springframework.beans.BeanUtils;

/**
 * @title:
 * @author: vegetableOnlyBecause
 * @date 2022/9/26 16:10
 * @description:
 */
public class UserTransUtils {
    public static UserVO dto2vo(UserDTO dto){
        if (null == dto){
            return null;
        }
        UserVO vo = new UserVO();
        BeanUtils.copyProperties(dto, vo);
        return vo;
    }

    public static UserCreateDTO vo2dto(UserCreate vo){
        if (null == vo) return null;
        UserCreateDTO dto = new UserCreateDTO();
        BeanUtils.copyProperties(vo, dto);
        return dto;
    }
}
