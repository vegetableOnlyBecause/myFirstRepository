package com.example.controller.user;

import com.example.controller.user.util.UserTransUtils;
import com.example.controller.user.vo.UserCreate;
import com.example.response.OperationResult;
import com.example.user.UserService;
import com.example.user.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping(value = "/v1/user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping(value="/create", method = RequestMethod.POST)
    public OperationResult<Object> create(@Validated @RequestBody UserCreate create) {
        return OperationResult.succ(userService.save(UserTransUtils.vo2dto(create)));
    }

    @RequestMapping(value="/{userId}", method = RequestMethod.GET)
    public OperationResult<Object> getUserById(@Validated @PathVariable(name = "userId") String userId)  {
        UserDTO dto = userService.getUserById(userId);
        return OperationResult.succ(UserTransUtils.dto2vo(dto));
    }

}
