package com.sdwl.video.controller;

import com.sdwl.video.exception.BaseException;
import com.sdwl.video.model.User;
import com.sdwl.video.service.IUserService;
import com.sdwl.video.util.response.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Creaed by fj on 2019/2/15
 */
@RestController
@RequestMapping(value = "users")
@Api("用户相关接口")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

    @Autowired
    IUserService userService;


    @GetMapping(value = "/getUserList")
    @ApiOperation(value="分页查询列表")
    public Page<User> getUserList(@RequestParam("pageNo")Integer pageNo
            , @RequestParam("pageSize")Integer pageSize) {
        return userService.getUserList(pageNo,pageSize);
    }


    /**
     * 登录
     * @param user
     */
    @PostMapping(value = "userLogin")
    @ApiOperation(value = "(后台)登录")
    public boolean  login(@RequestBody User user) throws BaseException {
       return userService.login(user);
    }


    /**
     * 修改密码
     * @param user
     */
    @PostMapping(value = "updateUser")
    @ApiOperation(value = "(后台)修改密码")
    public Integer  updateUser(@RequestBody User user) throws BaseException {
        return userService.updateUser(user);
    }

    /**
     * 添加账号
     * @param user
     */
    @PostMapping(value = "addUser")
    @ApiOperation(value = "(后台)新增用户")
    public Integer  addUser(@RequestBody User user) throws BaseException {
        return userService.addUser(user);
    }
}
