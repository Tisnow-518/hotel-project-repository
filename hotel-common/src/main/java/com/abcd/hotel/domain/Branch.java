package com.abcd.hotel.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("branch")
public class Branch {

    @TableId
    private Integer branchId;
    private String branchName;
    private String branchAddress;
    private String branchPhone;
    private int roomCount;
    private String branchPicUrl;

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
