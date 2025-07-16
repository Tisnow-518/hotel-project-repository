package com.abcd.hotel.domain;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data  /// 添加常见方法
@TableName("room")  /// 表名
public class Room extends ValueObject {

    @Schema(description = "房间编号")
    @TableId
    private Integer roomId;
    @Schema(description = "所属分店编号")
    private Integer branchId;
    @Schema(description = "房间号")
    private String roomNo;
    @Schema(description = "房间类型")
    private String roomType;
    @Schema(description = "房间设备")
    private String roomFacilities;
    @Schema(description = "房间状态")
    private String roomStatus;
    @Schema(description = "房间备注")
    private String roomRemark;

}
