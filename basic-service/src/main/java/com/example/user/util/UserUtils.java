package com.example.user.util;

import com.example.common.utils.MD5Utils;
import com.example.model.UserDO;
import com.example.user.dto.UserOprParamDTO;
import com.example.user.dto.UserDTO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import java.util.*;

/**
 * @title: 用户工具类
 * @author: vegetableOnlyBecause
 * @date 2022/9/26 15:13
 * @description:
 */
public class UserUtils {

    public static final String slat = "luan_ma";

    public static UserDTO do2Dto(UserDO userDO){
        if (null == userDO) {
            return null;
        }
        UserDTO dto = new UserDTO();
        BeanUtils.copyProperties(userDO, dto);
        return dto;
    }

    public static List<UserDTO> dos2Dtos(List<UserDO> dos){
        if (CollectionUtils.isEmpty(dos)){
            return Collections.emptyList();
        }
        List<UserDTO> dtos = new ArrayList<>();
        for (UserDO userDO : dos){
            UserDTO dto = do2Dto(userDO);
            if (null != dto){
                dtos.add(dto);
            }
        }
        return dtos;
    }

    public static UserDO dto2do(UserOprParamDTO dto){
        if (null == dto) {
            return null;
        }
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(dto, userDO);
        if (null == userDO.getId()) {
            int userId = (int) ((Math.random() * 9 + 1) * Math.pow(10, 10));
            userDO.setId(userId);
        }
        if (StringUtils.isNotBlank(dto.getPassword())) {
            userDO.setPassword(MD5Utils.string2MD5(dto.getPassword() + slat));
        }
        return userDO;
    }

}
