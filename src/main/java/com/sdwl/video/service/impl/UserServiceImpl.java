package com.sdwl.video.service.impl;

import com.github.pagehelper.PageHelper;
import com.sdwl.video.exception.BaseException;
import com.sdwl.video.mapper.UserMapper;
import com.sdwl.video.model.User;
import com.sdwl.video.service.IUserService;
import com.sdwl.video.util.MD5Util;
import com.sdwl.video.util.enums.StatEnum;
import com.sdwl.video.util.response.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Creaed by fj on 2018/10/31
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public boolean login(User user) throws BaseException {
        User userDb = userMapper.selectByUserName(user.getUserName());
        if(userDb==null){
            throw new BaseException(StatEnum.USER_NOT_EXIST);
        }
        String password = MD5Util.MD5Encode(user.getPassword(), "utf-8");
        if(!password.equals(userDb.getPassword())){
            throw new BaseException(StatEnum.PASSWORD_ERROR);
        }
        return true;
    }

    @Override
    public Integer updateUser(User user) {
        String password = MD5Util.MD5Encode(user.getPassword(), "utf-8");
        user.setPassword(password);
        return userMapper.updateByPrimaryKey(user);
    }

    @Override
    public Integer addUser(User user) {
        String password = MD5Util.MD5Encode(user.getPassword(), "utf-8");
        user.setPassword(password);
        return userMapper.insertSelective(user);
    }

    @Override
    public Page<User> getUserList(Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo,pageSize);
        List<User> userList = userMapper.selectAll();
        return new Page<>(userList);
    }
}
