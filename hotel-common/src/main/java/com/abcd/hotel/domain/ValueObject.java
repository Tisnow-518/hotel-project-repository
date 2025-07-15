package com.abcd.hotel.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;

import java.util.Date;

public abstract class ValueObject {

    //类别创建时间  (执行MP的自动FILL操作，当增加记录的时候）
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    //类别修改时间
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;

    @TableField(fill = FieldFill.INSERT)
    private String createdBy;
    @TableField(fill = FieldFill.UPDATE)
    private String updatedBy;

    @TableLogic
    private int isDeleted;
}
