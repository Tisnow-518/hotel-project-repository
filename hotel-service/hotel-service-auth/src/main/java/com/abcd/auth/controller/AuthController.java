package com.abcd.auth.controller;

import com.abcd.auth.service.AuthService;
import com.abcd.hotel.domain.Room;
import com.abcd.hotel.domain.User;
import com.abcd.hotel.utils.ResponseResult;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController  /// 控制器，返回结果处理为json
@RequestMapping("/user")  /// url前缀
public class AuthController {

    @Autowired
    private AuthService authService;

    /**
     * 根据用户编号获取用户信息
     * /api/user/{userId}
     */
    @Operation(summary = "根据用户编号获取用户信息")
    @GetMapping("/{userId}")
    public ResponseResult getUserById(@PathVariable Integer userId) throws Exception {

        User user = authService.getById(userId);

        if (user != null)
            return ResponseResult.success(user);
        else
            return ResponseResult.error("获取用户信息失败!");

    }

}
