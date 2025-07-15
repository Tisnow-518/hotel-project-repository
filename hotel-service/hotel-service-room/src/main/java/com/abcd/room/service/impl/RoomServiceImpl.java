package com.abcd.room.service.impl;

import com.abcd.hotel.domain.Room;
import com.abcd.hotel.mapper.RoomMapper;
import com.abcd.room.service.RoomService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImpl extends ServiceImpl<RoomMapper, Room> implements RoomService {

    @Override
    public Page<Room> loadPagedRoom(int pageNo, int pageSize) {

        Page<Room> page = new Page<>(pageNo, pageSize);
        return this.page(page);

    }

}
