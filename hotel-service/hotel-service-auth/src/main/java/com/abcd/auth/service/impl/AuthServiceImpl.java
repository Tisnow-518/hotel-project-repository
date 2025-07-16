package com.abcd.auth.service.impl;

import com.abcd.auth.service.AuthService;
import com.abcd.hotel.domain.User;
import com.abcd.hotel.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service  /// 服务
public class AuthServiceImpl extends ServiceImpl<UserMapper, User> implements AuthService {


    @Override
    public User getUserByUserName(String userName) {

        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();

        wrapper.eq(User::getUserName, userName)
                .last("limit 1");

        return getOne(wrapper);
    }

}
