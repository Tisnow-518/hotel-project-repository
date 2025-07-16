package com.abcd.hotel.domain;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Data  /// 添加常见方法
@TableName("branch")  /// 表名
public class Branch extends ValueObject {


    @Schema(description = "分店编号")
    @TableId(type = IdType.AUTO)
    private Integer branchId;
    @Schema(description = "分店名字")
    private String branchName;
    @Schema(description = "分店地址")
    private String branchAddress;
    @Schema(description = "分店电话")
    private String branchPhone;
    @Schema(description = "房间数")
    private Integer roomCount;
    @Schema(description = "分店照片")
    private String branchPicUrl;

    @Schema(description = "分店房间列表")
    @TableField(exist = false)
    private List<Room> roomList;

}
