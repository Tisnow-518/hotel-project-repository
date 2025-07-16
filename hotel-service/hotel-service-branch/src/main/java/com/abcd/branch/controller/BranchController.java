package com.abcd.branch.controller;

import com.abcd.branch.Feign.RoomFeignClient;
import com.abcd.hotel.domain.Branch;
import com.abcd.branch.service.BranchService;
import com.abcd.hotel.domain.Room;
import com.abcd.hotel.utils.ResponseResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController  /// 控制器，返回结果处理为json
@RequestMapping("/branch")
@Slf4j
@Tag(name="分店控制器")
public class BranchController {

    @Autowired
    private BranchService branchService;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private LoadBalancerClient loadBalancerClient;
    @Autowired
    private RoomFeignClient roomFeignClient;

    /**
     * 创建分店，分店名称、地址、电话不能为空
     * /api/branch/save
     */
    @Operation(summary = "创建分店")
    @PostMapping("/save")
    public ResponseResult createBranch(@RequestBody Branch branch) throws Exception {

        boolean result = branchService.save(branch);

        if(result)
            return ResponseResult.success();
        else
            return ResponseResult.error("创建分店失败!");

    }

    /**
     * 删除分店，需要指定分店编号
     * /api/branch/delete/{branchId}
     */
    @Operation(summary = "删除分店")
    @DeleteMapping("/delete/{branchId}")
    public ResponseResult removeBranch(@PathVariable Integer branchId) throws Exception {

        boolean result = branchService.removeById(branchId);

        if(result)
            return ResponseResult.success();
        else
            return ResponseResult.error("删除分店失败!");

    }

    /**
     * 修改分店，需要指定分店编号
     * /api/branch/update
     */
    @Operation(summary = "修改分店")
    @PutMapping("/update")
    public ResponseResult updateBranch(@RequestBody Branch branch) throws Exception {

        boolean result = branchService.updateById(branch);

        if(result)
            return ResponseResult.success();
        else
            return ResponseResult.error("修改失败!");

    }

    /**
     * 获取分店编号，需要指定分店名
     * /api/branch/branchId/branchName/{branchName}
     */
    @Operation(summary = "根据分店名获取分店编号")
    @GetMapping("/branchId/branchName/{branchName}")
    public Integer getBranchIdByBranchName(@PathVariable String branchName) throws Exception {

        Integer branchId = branchService.getBranchIdByBranchName(branchName);

        return branchId;

    }


    @Operation(summary = "分店分页")
    @GetMapping("list")  /// api/branch/list?pageNo=
    public ResponseResult loadPagedList(int pageNo) throws Exception{

        Page<Branch> page = branchService.loadPagedBranch(pageNo, 10);

        if(page!=null)
            return ResponseResult.success(page);
        else
            return ResponseResult.error("查询分店分页信息失败!");
    }

    /**
     * 根据分店编号查询分店信息
     */
    @Operation(summary = "根据分店编号查询分店信息")
    @GetMapping("/{branchId}")
    public ResponseResult getBranchById(@PathVariable Integer branchId) throws Exception {

        log.info("开始根据分店编号获取分店信息...");

        System.out.println("load branch, id = " + branchId);
        Branch branch = branchService.getById(branchId);
        //branch.setRoomList(this.loadRemoteRoomsByBranchIdWithLoadBalanceAnnotation(branchId));

        ResponseResult data = roomFeignClient.getRoomListByBranchId(branchId);
        List<Room> roomList = (List<Room>) data.getData();
        branch.setRoomList(roomList);

        log.info("通过OpenFeign获得数据，得到房间数量: " + roomList.size());

        ResponseResult<Branch> result = ResponseResult.success(branch);
        result.setMsg("分店信息加载完成!");

        return result;

    }

    /**
     * 根据分店编号加载房间列表，直接使用url
     */
    private List<Room> loadRemoteRoomsByBranchId(Integer branchId){

        String url = "http://localhost:5000/api/room/branchId/" + branchId;

        ResponseResult resultObj = restTemplate.getForObject(url, ResponseResult.class);

        List<Room> roomList = (List<Room>)resultObj.getData();

        log.info("url: " + url + ", room count: " + roomList.size());

        return roomList;

    }

    /**
     * 根据分店编号加载房间列表，实现loadbalance
     */
    private List<Room> loadRemoteRoomsByBranchIdWithLoadBalance(Integer branchId){

        ServiceInstance instance = loadBalancerClient.choose("hotel-service-room");
        String url = instance.getUri() + "/api/room/branchId/" + branchId;

        ResponseResult resultObj = restTemplate.getForObject(url, ResponseResult.class);

        List<Room> roomList = (List<Room>)resultObj.getData();

        log.info("url: " + url + ", room count: " + roomList.size());

        return roomList;

    }

    /**
     * 根据分店编号加载房间列表，使用注解实现loadbalance
     */
    private List<Room> loadRemoteRoomsByBranchIdWithLoadBalanceAnnotation(Integer branchId){

        String url = "http://hotel-service-room/api/room/branchId/" + branchId;

        ResponseResult resultObj = restTemplate.getForObject(url, ResponseResult.class);

        List<Room> roomList = (List<Room>)resultObj.getData();

        log.info("url: " + url + ", room count: " + roomList.size());

        return roomList;

    }

}
