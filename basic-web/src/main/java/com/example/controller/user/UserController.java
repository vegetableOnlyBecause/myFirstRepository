package com.example.controller.user;

import com.example.controller.user.util.UserTransUtils;
import com.example.controller.user.vo.UserOprParam;
import com.example.controller.user.vo.UserVO;
import com.example.response.Result;
import com.example.response.ResultEnum;
import com.example.response.ResultUtil;
import com.example.user.UserService;
import com.example.user.dto.UserDTO;
import com.example.user.dto.UserOprParamDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@Slf4j
@Configuration
@RestController
@RequestMapping(value = "/market/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 注册
     * @param param 所需数据
     * @return Result
     */
    @PostMapping(value = "/sign-up")
    public Result register(@Valid UserOprParam param) {
        if (null != userService.getUserByUserName(param.getUsername())) {
            return ResultUtil.error(ResultEnum.USER_DUPLICATE);
        }
        userService.save(UserTransUtils.vo2dto(param));
        return ResultUtil.success(ResultEnum.SIGN_UP);
    }

    /**
     * 更新基本数据
     * @param param 所需数据
     * @return Result
     */
    @PostMapping(value = "/update/info")
    public Result updateUserByName(UserOprParam param) {
        boolean flag = userService.update(UserTransUtils.vo2dto(param));
        if (!flag) {
            ResultUtil.error(ResultEnum.UPDATE_USER_ERROR);
        }
        return ResultUtil.success(ResultEnum.UPDATE_USER);
    }

    @Transactional
    @PostMapping(value = "/update/password")
    public Result updatePassword(@RequestParam("username") String username,
                                 @RequestParam("oldpd") String oldpd,
                                 @RequestParam("password") String password){
        UserDTO user = userService.getUserByUserName(username);
        if (user == null){
            return ResultUtil.error(ResultEnum.USER_MISSED);
        }
        if (!UserTransUtils.checkPassword(oldpd, user.getPassword())){
            return ResultUtil.error(ResultEnum.WRONG_PASSWORD);
        }
        if (UserTransUtils.checkPassword(password, user.getPassword())){
            return ResultUtil.error(ResultEnum.PASSWORD_NOT_CHANGE);
        }
        UserOprParamDTO param = new UserOprParamDTO();
        param.setId(user.getId());
        param.setPassword(password);
        userService.update(param);
        return ResultUtil.success(ResultEnum.PASSWORD_UPDATED);
    }

    /**
     * 登录
     * @param username 用户名
     * @param password 密码
     * @param power 权限
     * @return Result
     */
    @PostMapping(value = "/sign-in")
    public Result SignIn(@RequestParam("username") String username,
                         @RequestParam("password") String password,
                         @RequestParam("power") Integer power){
        UserDTO user = userService.getUserByUserName(username);
        if (user == null){
            return ResultUtil.error(ResultEnum.USER_MISSED);
        }
        if (!UserTransUtils.checkPassword(password, user.getPassword())){
            return ResultUtil.error(ResultEnum.WRONG_PASSWORD);
        }
        if (!user.getPower().equals(power)){
            return ResultUtil.error(ResultEnum.PERMISSION_DENIED);
        }
        return ResultUtil.success(ResultEnum.SIGN_IN);
    }

    /**
     * 根据用户名获取用户信息
     * @param username 用户名
     * @return Result
     */
    @GetMapping(value = "/info/{username}")
    public Result<UserVO> getUserInfo(@PathVariable("username") String username){
        UserDTO user = userService.getUserByUserName(username);
        if (user == null){
            return ResultUtil.error(ResultEnum.USER_MISSED);
        }
        return ResultUtil.success(ResultEnum.GET_ONE_USER_INFO, UserTransUtils.dto2vo(user));
    }

    /**
     * 获取用户的所有信息，本项目中的使用：在物品详情页获取卖家信息
     * @param id 用户id
     * @return Result
     */
    @GetMapping(value = "/id/{id}")
    public Result<UserVO> getUserById(@PathVariable("id") Integer id){
        UserDTO user = userService.getUserById(id);
        return ResultUtil.success(ResultEnum.GET_ONE_USER_INFO, UserTransUtils.dto2vo(user));
    }


}
