package com.abcd.branch.Feign;

import com.abcd.hotel.utils.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "hotel-service-room")
public interface RoomFeignClient {

    /**
     * 根据分店编号删除房间
     * /api/room/delete/branchId/{branchId}
     */
    @DeleteMapping("/api/room/delete/branchId/{branchId}")
    ResponseResult removeRoomsByBranchId(@PathVariable Integer branchId);

    @GetMapping("/api/room/branchId/{branchId}")
    ResponseResult getRoomListByBranchId(@PathVariable Integer branchId);

}
