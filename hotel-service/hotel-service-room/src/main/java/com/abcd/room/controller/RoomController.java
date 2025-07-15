package com.abcd.room.controller;

import com.abcd.hotel.domain.Room;
import com.abcd.hotel.mapper.RoomMapper;
import com.abcd.hotel.utils.ResponseResult;
import com.abcd.room.service.RoomService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/room")
@Slf4j
@Tag(name="类别控制器")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @Operation(summary = "加载房间分页信息")
    @GetMapping("list")  /// api/room/list?pageNo=
    public ResponseResult loadPagedList(int pageNo) throws Exception {

        Page<Room> page = roomService.loadPagedRoom(pageNo, 10);
        if(page!=null)
            return ResponseResult.success(page);
        else
            return ResponseResult.error("加载房间分页信息失败!");

    }

}
