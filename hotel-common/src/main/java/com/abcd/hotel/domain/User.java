package com.abcd.hotel.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user")
public class User extends ValueObject {

    @TableId
    private Integer userId;
    private String userName;
    private String userPassword;
    private String userRole;

}
