package com.abcd.hotel.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("branch")
public class Branch extends ValueObject {

    @TableId
    private Integer branchId;
    private String branchName;
    private String branchAddress;
    private String branchPhone;
    private Integer roomCount;
    private String branchPicUrl;

}
