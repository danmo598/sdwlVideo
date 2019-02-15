package com.sdwl.video.service;


import com.sdwl.video.exception.BaseException;
import com.sdwl.video.model.User;
import com.sdwl.video.util.response.Page;

/**
 * Creaed by fj on 2018/10/31
 */
public interface IUserService {
    boolean login(User user) throws BaseException;

    Integer updateUser(User user);

    Integer addUser(User user);

    Page<User> getUserList(Integer pageNo, Integer pageSize);
}
