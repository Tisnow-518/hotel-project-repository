package com.abcd.hotel.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.util.Date;

/**
 * 值对象抽象类
 * 包含所有实体类的公共字段：审计字段和软删除字段
 */
@Data
public abstract class ValueObject {

    /**
     * 创建时间 (执行MP的自动FILL操作，当增加记录的时候)
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 修改时间 (执行MP的自动FILL操作，当更新记录的时候)
     */
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;

    /**
     * 创建人 (执行MP的自动FILL操作，当增加记录的时候)
     */
    @TableField(fill = FieldFill.INSERT)
    private String createdBy;

    /**
     * 修改人 (执行MP的自动FILL操作，当更新记录的时候)
     */
    @TableField(fill = FieldFill.UPDATE)
    private String updatedBy;

    /**
     * 逻辑删除字段 (0未删，1已删)
     */
    @TableLogic
    private int isDeleted;
}
