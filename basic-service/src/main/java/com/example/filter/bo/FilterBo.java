package com.example.filter.bo;

import com.example.good.dto.GoodDTO;
import com.example.user.dto.UserDTO;
import lombok.Builder;
import lombok.Data;

/**
 * @title:
 * @author: vegetableOnlyBecause
 * @date 2023/7/12 11:32
 * @description:
 */
@Data
@Builder
public class FilterBO {

    private Integer userId;

    private UserDTO userDTO;

    private Integer goodId;

    private GoodDTO goodDTO;
}
