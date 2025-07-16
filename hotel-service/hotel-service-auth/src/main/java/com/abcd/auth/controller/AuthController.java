package com.abcd.auth.controller;

import com.abcd.auth.service.AuthService;
import com.abcd.hotel.domain.Room;
import com.abcd.hotel.domain.User;
import com.abcd.hotel.utils.JwtUserToken;
import com.abcd.hotel.utils.ResponseResult;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController  /// 控制器，返回结果处理为json
@RequestMapping("/user")  /// url前缀
public class AuthController {

    @Autowired
    private AuthService authService;
    @Autowired
    private JwtUserToken jwtUserToken;

    /**
     * 登录
     * /api/user/login
     */
    @Operation(summary = "登录")
    @PostMapping("/login")
    public ResponseResult login(@RequestBody User user) {

        User user1 = authService.getUserByUserName(user.getUserName());

        if (user1 == null)
            return ResponseResult.error("用户名错误!");

        if (!user1.getUserPassword().equals(user.getUserPassword()))
            return ResponseResult.error("密码错误");

        String token = jwtUserToken.getToken(user);
        HashMap<String, Object> data = new HashMap<>();
        data.put("token", token);
        data.put("user", user1);
        return ResponseResult.success(data);
    }



    /**
     * 创建用户
     * /api/user/save
     */
    @Operation(summary = "创建用户")
    @PostMapping("/save")
    public ResponseResult createUser(@RequestBody User user) throws Exception {

        boolean result = authService.save(user);

        if (result)
            return ResponseResult.success();
        else
            return ResponseResult.error("创建用户失败!");

    }

    /**
     * 删除用户
     * /api/user/delete/{userId}
     */
    @Operation(summary = "删除用户")
    @DeleteMapping("/delete/{userId}")
    public ResponseResult removeUser(@PathVariable Integer userId) throws Exception {

        boolean result = authService.removeById(userId);

        if (result)
            return ResponseResult.success();
        else
            return ResponseResult.error("删除用户失败!");

    }

    /**
     * 修改用户
     * /api/user/update
     */
    @Operation(summary = "修改用户")
    @PutMapping("/update")
    public ResponseResult updateUser(@RequestBody User user) throws Exception {

        boolean result = authService.updateById(user);

        if (result)
            return ResponseResult.success();
        else
            return ResponseResult.error("删除用户失败!");

    }

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
