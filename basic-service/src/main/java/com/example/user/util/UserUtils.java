package com.example.user.util;

import com.example.model.CommonUserDO;
import com.example.user.dto.UserCreateDTO;
import com.example.user.dto.UserDTO;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.util.DigestUtils;

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
        if (null == userDO) return null;
        UserDTO dto = new UserDTO();
        dto.setUserId(userDO.getUserId());
        dto.setUserName(userDO.getUserName());
        dto.setNickName(userDO.getNickName());
        dto.setPhoneNumber(userDO.getPhoneNumber());
        dto.setBirthday(userDO.getBirthday());
        dto.setEmail(userDO.getEmail());
        dto.setRegisterTime(userDO.getRegisterTime());
        return dto;
    }

    public static List<UserDTO> dos2Dtos(List<CommonUserDO> dos){
        if (CollectionUtils.isEmpty(dos)){
            return Collections.EMPTY_LIST;
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
        if (null == dto){
            return null;
        }
        String userId = UUID.randomUUID().toString();
        CommonUserDO userDO = new CommonUserDO();
        userDO.setUserId(userId);
        userDO.setUserName(dto.getUserName());
        userDO.setPassword(DigestUtils.md5DigestAsHex((dto.getPassword() + slat).getBytes()));
        userDO.setNickName(dto.getNickName());
        userDO.setPhoneNumber(dto.getPhoneNumber());
        userDO.setBirthday(dto.getBirthday());
        userDO.setEmail(dto.getEmail());
        userDO.setRegisterTime(new Date());
        userDO.setUpdateTime(new Date());
        return userDO;
    }

}
