package com.example.controller.user.util;

import com.example.common.utils.MD5Utils;
import com.example.controller.user.vo.UserOprParam;
import com.example.controller.user.vo.UserVO;
import com.example.user.dto.UserOprParamDTO;
import com.example.user.dto.UserDTO;
import com.example.user.util.UserUtils;
import org.apache.commons.lang3.StringUtils;
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

    public static UserOprParamDTO vo2dto(UserOprParam vo){
        if (null == vo) return null;
        UserOprParamDTO dto = new UserOprParamDTO();
        BeanUtils.copyProperties(vo, dto);
        if (StringUtils.isNotBlank(vo.getUsername())
            && !"null".equals(vo.getUsername())) {
            dto.setUserName(vo.getUsername());
        }
        return dto;
    }

    public static boolean checkPassword(String inputPs, String dbPs) {
        if (StringUtils.isBlank(inputPs) || StringUtils.isBlank(dbPs)) {
            return false;
        }
        return MD5Utils.passwordIsTrue(inputPs + UserUtils.slat, dbPs);
    }
}
