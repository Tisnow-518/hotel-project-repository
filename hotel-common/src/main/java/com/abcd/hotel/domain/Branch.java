package com.abcd.hotel.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("branch")
public class Branch extends ValueObject {

    @TableId(type = IdType.AUTO)
    private Integer branchId;
    private String branchName;
    private String branchAddress;
    private String branchPhone;
    private Integer roomCount;
    private String branchPicUrl;

    @TableField(exist = false)
    private List<Room> roomList;

}
