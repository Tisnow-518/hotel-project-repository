package com.abcd.hotel.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 用户实体类
 * 对应数据库表：user
 */
@Data
@TableName("user")
public class User extends ValueObject {

    /**
     * 用户ID，主键
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    /**
     * 用户名
     */
    @TableField("username")
    private String username;

    /**
     * 密码（加密）
     */
    @TableField("password")
    private String password;

    /**
     * 角色
     * 可选值：ADMIN、MANAGER、USER
     */
    @TableField("role")
    private String role;
}
