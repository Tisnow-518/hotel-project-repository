package com.abcd.hotel.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data  /// 添加常见方法
@TableName("user")  /// 表名
public class User extends ValueObject {

    @TableId
    private Integer userId;
    private String userName;
    private String userPassword;
    private String userRole;

}
