package com.example.user.util;

import com.example.common.utils.MD5Utils;
import com.example.common.utils.OprUtils;
import com.example.model.UserDO;
import com.example.user.dto.UserOprParamDTO;
import com.example.user.dto.UserDTO;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.function.Consumer;

/**
 * @title: 用户工具类
 * @author: vegetableOnlyBecause
 * @date 2022/9/26 15:13
 * @description:
 */
public class UserUtils {

    public static final String slat = "luan_ma";

    public static UserDTO do2Dto(UserDO userDO){
        return OprUtils.copyModel2Model(userDO, new UserDTO());
    }

    public static UserDO dto2do(UserOprParamDTO dto){
        Consumer<UserDO> consumer =  user -> {
            if (null == user.getId()) {
                int userId = initId();
                user.setId(userId);
            }
            if (StringUtils.isNotBlank(dto.getPassword())) {
                user.setPassword(MD5Utils.string2MD5(dto.getPassword() + slat));
            }
        };
        return OprUtils.model2Model(dto, new UserDO(), consumer);
    }

    public static int initId() {
        StringBuilder str=new StringBuilder();//定义变长字符串
        Random random=new Random();
        for(int i=0;i<8;i++){
            str.append(random.nextInt(10));
        }
        return Integer.parseInt(str.toString());
    }

}
