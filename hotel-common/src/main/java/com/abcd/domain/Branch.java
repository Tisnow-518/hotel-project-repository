package com.abcd.domain;

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
    private Integer roomCount;
    private String photoUrl;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill=FieldFill.UPDATE)
    private Date updateTime;

    @TableField(fill = FieldFill.INSERT)
    private String createdBy;
    @TableField(fill=FieldFill.UPDATE)
    private String updatedBy;

    @TableLogic
    private int isDeleted;

}
