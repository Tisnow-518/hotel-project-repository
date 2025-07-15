package com.abcd.room.service;

import com.abcd.hotel.domain.Room;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface RoomService extends IService<Room> {

    /**
     * 根据分店编号加载房间列表，并按房间编号升序排序
     */
    List<Room> loadRoomsByBranchId(Integer branchId);

    Page<Room> loadPagedRoom(int pageNo, int pageSize);

}
