package com.abcd.hotel.utils;

import com.abcd.hotel.domain.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUserToken {

    private static final long EXPIRE_TIME = 50*60*1000;

    /**
     * 根据提供的用户信息中的userName和password生成web token
     */
    public String getToken(User user) {

        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        String token = "";
        token = JWT.create().withAudience(user.getUserName())       //将用户名保存到token中
                .withExpiresAt(date)
                .sign(Algorithm.HMAC256(user.getUserPassword()));   //以用户密码作为token的密码

        return token;
    }

}
