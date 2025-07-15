package com.abcd.branch.Feign;

import com.abcd.hotel.utils.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "hotel-service-room")
public interface RoomFeignClient {

    @GetMapping("/api/room/branchId/{branchId}")
    ResponseResult getRoomListByBranchId(@PathVariable Integer branchId);

}
