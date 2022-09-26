package com.example.controller.user;

import com.example.controller.user.utils.UserUtil;
import com.example.controller.user.vo.UserCreate;
import com.example.response.OperationResult;
import com.example.user.UserService;
import com.example.user.dto.UserCreateDTO;
import com.example.user.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

@Slf4j
@RestController
@RequestMapping(value = "/v1/user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping(value="/create", method = RequestMethod.POST)
    public OperationResult<Object> create(@Validated @RequestBody UserCreate create)  {
        userService.saveUserInfo(UserUtil.vo2dto(create));
        return OperationResult.succ(null);
    }


    @RequestMapping(value="/{userId}", method = RequestMethod.GET)
    public OperationResult<Object> getUserById(@Validated @PathVariable(name = "userId") String userId)  {
        UserDTO dto = userService.getUserById(userId);
        return OperationResult.succ(UserUtil.dto2vo(dto));
    }


}
