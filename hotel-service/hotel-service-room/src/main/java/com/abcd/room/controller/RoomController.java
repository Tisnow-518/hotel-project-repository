package com.abcd.room.controller;

import com.abcd.hotel.domain.Room;
import com.abcd.hotel.utils.ResponseResult;
import com.abcd.room.Feign.BranchFeignClient;
import com.abcd.room.Feign.MoneyExchangeFeignClient;
import com.abcd.room.service.RoomService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController  /// 控制器，返回结果处理为json
@RequestMapping("/api/room")  /// url前缀
@RefreshScope  /// 配置的动态刷新
@Slf4j
@Tag(name="房间控制器")
public class RoomController {

    @Autowired
    private RoomService roomService;
    @Autowired
    private BranchFeignClient branchFeignClient;
    @Autowired
    private MoneyExchangeFeignClient moneyExchangeFeignClient;

    @Value("${room.price}")
    private String price;

    /**
     * 创建房间
     * /api/room/save
     */
    @Operation(summary = "创建房间")
    @PostMapping("/save")
    public ResponseResult createRoom(@RequestBody Room room) throws Exception {

        boolean result = roomService.save(room);

        if (result)
            return ResponseResult.success();
        else
            return ResponseResult.error("创建房间失败!");

    }

    /**
     * 删除房间
     * /api/room/delete/{roomId}
     */
    @Operation(summary = "删除房间")
    @DeleteMapping("/delete/{roomId}")
    public ResponseResult removeRoom(@PathVariable Integer roomId) throws Exception {

        boolean result = roomService.removeById(roomId);

        if (result)
            return ResponseResult.success();
        else
            return ResponseResult.error("删除房间失败!");

    }

    /**
     * 根据分店编号删除房间
     * /api/room/delete/branchId/{branchId}
     */
    @Operation(summary = "根据分店编号删除房间")
    @DeleteMapping("/delete/branchId/{branchId}")
    public ResponseResult removeRoomByBranchId(@PathVariable Integer branchId) throws Exception {

        List<Integer> roomsId = roomService.getRoomsIdByBranchId(branchId);

        boolean result = roomService.removeByIds(roomsId);

        if (result)
            return ResponseResult.success();
        else
            return ResponseResult.error("删除房间失败!");

    }

    /**
     * 修改房间
     * /api/room/update
     */
    @Operation(summary = "修改房间")
    @PutMapping("/update")
    public ResponseResult updateRoom(@RequestBody Room room) throws Exception {

        boolean result = roomService.updateById(room);

        if (result)
            return ResponseResult.success();
        else
            return ResponseResult.error("修改房间失败!");

    }


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
    @Operation(summary = "根据房间编号获取房间信息")
    @GetMapping("/{roomId}")
    public ResponseResult getRoomById(@PathVariable Integer roomId) throws Exception {

        Room room = roomService.getById(roomId);

        ResponseResult result = ResponseResult.success(room);

        ResponseResult exchangeResult = moneyExchangeFeignClient.getExchange("a1376bdea11b34f555f65811934663e8",
                "CNY", "USD", price);

        Map<String,Object> data = (Map<String, Object>) exchangeResult.getData();
        String priceUSD = (String) data.get("money");

        result.setMsg("获取房间信息成功，房间价格(CNY)：" + price + "，房间价格(USD)：" + priceUSD);

        if (room != null)
            return result;
        else
            return ResponseResult.error("获取房间信息失败!");

    }

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
