package com.abcd.hotel.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 房间实体类
 * 对应数据库表：room
 */
@Data
@TableName("room")
public class Room extends ValueObject {

    /**
     * 房间ID，主键
     */
    @TableId(value = "room_id", type = IdType.AUTO)
    private Integer roomId;

    /**
     * 分店ID，外键
     */
    @TableField("room_branch_id")
    private Integer roomBranchId;

    /**
     * 房号
     */
    @TableField("room_no")
    private String roomNo;

    /**
     * 房间类型
     * 可选值：普单人间、普双人间、三人间、商务套房、贵宾套房
     */
    @TableField("room_type")
    private String roomType;

    /**
     * 房间设施
     * 多个设施用逗号分隔，如：平面液晶电视,冰箱,空调,卫星电视,互联网接入,冲浪浴缸,观海景
     */
    @TableField("room_facilities")
    private String roomFacilities;

    /**
     * 房间状态
     * 可选值：未入住、有住客、已预订、保洁中、已退房未保洁、维修中
     */
    @TableField("room_status")
    private String roomStatus;

    /**
     * 备注信息
     */
    @TableField("room_remark")
    private String roomRemark;

    /**
     * 关联的分店信息（非数据库字段）
     */
    @TableField(exist = false)
    private Branch branch;
}
