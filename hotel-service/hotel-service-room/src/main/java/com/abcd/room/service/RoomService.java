package com.abcd.room.service;

import com.abcd.hotel.domain.Room;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface RoomService extends IService<Room> {

    /**
     * 根据分店编号获取房间编号
     */
    List<Integer> getRoomsIdByBranchId(Integer branchId);

    /**
     * 根据房间编号获取分店编号
     */
    Integer getBranchIdByRoomId(Integer roomId);

    /**
     * 根据分店编号加载房间列表，并按房间编号升序排序
     */
    List<Room> loadRoomsByBranchId(Integer branchId);

    /**
     * 根据房间类型加载房间列表
     */
    List<Room> loadRoomsByRoomType(String roomType);

    /**
     * 根据房间状态加载房间列表
     */
    List<Room> loadRoomsByRoomStatus(String roomStatus);

    /**
     * 加载房间分页信息
     */
    Page<Room> loadPagedRoom(int pageNo, int pageSize);



}
