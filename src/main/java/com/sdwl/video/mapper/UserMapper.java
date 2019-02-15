package com.sdwl.video.mapper;

import com.sdwl.video.model.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper extends CommonMapper<User> {

    User selectByUserName(@Param("userName") String userName);
}