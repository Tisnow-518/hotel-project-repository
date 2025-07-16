package com.abcd.room.controller;

import com.abcd.hotel.domain.Room;
import com.abcd.hotel.utils.ResponseResult;
import com.abcd.room.Feign.BranchFeignClient;
import com.abcd.room.service.RoomService;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
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

@RestController  /// 控制器，返回结果处理为json
@RequestMapping("/room")  /// url前缀
@Slf4j
@Tag(name="房间控制器")
public class RoomController {

    @Autowired
    private RoomService roomService;
    @Autowired
    private BranchFeignClient branchFeignClient;

    @SentinelResource(value="loadPagedList")
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
     * 根据房间编号获取房间信息
     * /api/room/{roomId}
     */
    @SentinelResource(value="getRoomById")
    @Operation(summary = "根据房间编号获取房间信息")
    @GetMapping("/{roomId}")
    public ResponseResult getRoomById(@PathVariable Integer roomId) throws Exception {

        Room room = roomService.getById(roomId);

        if (room != null)
            return ResponseResult.success(room);
        else
            return ResponseResult.error("获取房间信息失败!");

    }

    @SentinelResource(value="loadRoomsByBranchName")
    @Operation(summary = "根据分店名称获取房间列表")
    @GetMapping("/branchName/{branchName}")
    public ResponseResult <List<Room>> loadRoomsByBranchName(@PathVariable String branchName) throws Exception {

        Integer branchId = branchFeignClient.getBranchIdByBranchName(branchName);

        if(branchId==null)
            return ResponseResult.error("加载房间列表失败!");
        else {
            List<Room> rooms = roomService.loadRoomsByBranchId(branchId);

            if(rooms==null) {
                log.info("加载房间列表失败!");
            }
            else {
                ResponseResult responseResult = ResponseResult.success(rooms);
                log.info("房间信息加载成功，分店编号为: "+branchId+", 房间数量: "+rooms.size());
                responseResult.setMsg("房间信息加载成功，分店编号为: "+branchId+", 房间数量: "+rooms.size());
                return responseResult;
            }
        }
        return ResponseResult.error("加载房间列表失败!");
    }

    @SentinelResource(value="loadRoomsByRoomType")
    @Operation(summary = "根据房间类型获取房间列表")
    @GetMapping("/roomType/{roomType}")
    public ResponseResult <List<Room>> loadRoomsByRoomType(@PathVariable String roomType) throws Exception {

        List<Room> rooms = roomService.loadRoomsByRoomType(roomType);

        if(rooms == null) {
            log.info("加载房间列表失败!");
        }
        else {
            ResponseResult responseResult = ResponseResult.success(rooms);
            log.info("房间信息加载成功，房间类型为: " + roomType + ", 房间数量: "+rooms.size());
            responseResult.setMsg("房间信息加载成功，房间类型为: " + roomType + ", 房间数量: "+rooms.size());
            return responseResult;
        }
        return ResponseResult.error("加载房间列表失败!");
    }

    @SentinelResource(value="loadRoomsByRoomStatus")
    @Operation(summary = "根据房间状态获取房间列表")
    @GetMapping("/roomStatus/{roomStatus}")
    public ResponseResult <List<Room>> loadRoomsByRoomStatus(@PathVariable String roomStatus) throws Exception {

        List<Room> rooms = roomService.loadRoomsByRoomStatus(roomStatus);

        if(rooms == null) {
            log.info("加载房间列表失败!");
        }
        else {
            ResponseResult responseResult = ResponseResult.success(rooms);
            log.info("房间信息加载成功，房间状态为: " + roomStatus + ", 房间数量: "+rooms.size());
            responseResult.setMsg("房间信息加载成功，房间状态为: " + roomStatus + ", 房间数量: "+rooms.size());
            return responseResult;
        }
        return ResponseResult.error("加载房间列表失败!");
    }





    /**
     * 根据分店编号加载房间列表
     */
    @SentinelResource(value="loadRoomsByBranchId")
    @Operation(summary = "根据分店编号加载房间列表")
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
