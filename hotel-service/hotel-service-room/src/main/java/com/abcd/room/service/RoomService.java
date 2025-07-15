package com.abcd.room.service;

import com.abcd.hotel.domain.Room;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface RoomService extends IService<Room> {

    Page<Room> loadPagedRoom(int pageNo, int pageSize);

}
