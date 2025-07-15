package com.abcd.room.Feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "hotel-service-branch")
public interface BranchFeignClient {

    @GetMapping("api/branch/branchId/branchName/{branchName}")
    Integer getBranchIdByBranchName(@PathVariable String branchName);

}
