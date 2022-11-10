package com.example.user.util;

import com.example.common.utils.MD5Utils;
import com.example.model.CommonUserDO;
import com.example.user.dto.UserCreateDTO;
import com.example.user.dto.UserDTO;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;

import java.util.*;

/**
 * @title: 用户工具类
 * @author: vegetableOnlyBecause
 * @date 2022/9/26 15:13
 * @description:
 */
public class UserUtils {

    private static final String slat = "luan_ma";

    public static UserDTO do2Dto(CommonUserDO userDO){
        if (null == userDO) {
            return null;
        }
        UserDTO dto = new UserDTO();
        BeanUtils.copyProperties(userDO, dto);
        return dto;
    }

    public static List<UserDTO> dos2Dtos(List<CommonUserDO> dos){
        if (CollectionUtils.isEmpty(dos)){
            return Collections.emptyList();
        }
        List<UserDTO> dtos = new ArrayList<>();
        for (CommonUserDO userDO : dos){
            UserDTO dto = do2Dto(userDO);
            if (null != dto){
                dtos.add(dto);
            }
        }
        return dtos;
    }

    public static CommonUserDO dto2do(UserCreateDTO dto){
        if (null == dto) {
            return null;
        }
        String userId = UUID.randomUUID().toString();
        CommonUserDO userDO = new CommonUserDO();
        BeanUtils.copyProperties(dto, userDO);
        userDO.setUserId(userId);
        userDO.setPassword(MD5Utils.convertMD5(dto.getPassword() + slat));
        userDO.setRegisterTime(new Date());
        userDO.setUpdateTime(new Date());
        return userDO;
    }

}
