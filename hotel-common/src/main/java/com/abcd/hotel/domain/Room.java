package com.abcd.hotel.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("room")
public class Room extends ValueObject {

    @TableId
    private Integer roomId;
    private Integer branchId;
    private String roomNo;
    private String roomType;
    private String roomFacilities;
    private String roomStatus;
    private String roomRemark;

}
