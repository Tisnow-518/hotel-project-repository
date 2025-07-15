package com.abcd.room.controller;

import com.abcd.hotel.domain.Room;
import com.abcd.hotel.utils.ResponseResult;
import com.abcd.room.service.RoomService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    /**
     * 根据分店编号加载房间列表
     */
    @GetMapping("/branchId/{branchId}")
    public ResponseResult <List<Room>> loadRoomsByBranchId(@PathVariable Integer branchId) throws Exception {

        List<Room> rooms = roomService.loadRoomsByBranchId(branchId);

        if(rooms==null) {
            log.info("加载房间信息失败!");
        }
        else {
            ResponseResult responseResult = ResponseResult.success(rooms);
            log.info("房间信息加载成功，分店编号为: "+branchId+", 房间数量: "+rooms.size());
            responseResult.setMsg("房间信息加载成功，分店编号为: "+branchId+", 房间数量: "+rooms.size());
            return responseResult;
        }

        return ResponseResult.error("加载房间信息失败!");

    }

}
