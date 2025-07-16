package com.abcd.auth.service;

import com.abcd.hotel.domain.User;
import com.abcd.hotel.utils.ResponseResult;
import com.baomidou.mybatisplus.extension.service.IService;

public interface AuthService extends IService<User> {

    /**
     * 根据用户名获取用户
     */
    public User getUserByUserName(String userName);

}
