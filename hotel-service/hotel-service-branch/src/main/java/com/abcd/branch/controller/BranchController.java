package com.abcd.branch.controller;

import com.abcd.branch.Feign.RoomFeignClient;
import com.abcd.branch.properties.WorkProperties;
import com.abcd.branch.service.BranchService;
import com.abcd.hotel.domain.Branch;
import com.abcd.hotel.domain.Room;
import com.abcd.hotel.utils.ResponseResult;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController                           /// 控制器，返回结果处理为json
@RequestMapping("/api/branch")         /// url前缀
@RefreshScope                             /// 配置的动态刷新
@Slf4j
@Tag(name="分店控制器")
public class BranchController {

    @Autowired
    private BranchService branchService;
    @Autowired
    private RoomFeignClient roomFeignClient;
    @Autowired
    private WorkProperties workProperties;

    @Value("${branch.rank}")            /// Nacos 配置
    private Integer rank;
    @Value("${branch.capacity}")        /// Nacos 配置
    private Integer capacity;

    /**
     * 创建分店
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
     * 删除分店
     * /api/branch/delete/{branchId}
     */
    @Operation(summary = "删除分店")
    @DeleteMapping("/delete/{branchId}")
    public ResponseResult removeBranch(@PathVariable Integer branchId) throws Exception {

        roomFeignClient.removeRoomsByBranchId(branchId);

        boolean result = branchService.clearRoomCount(branchId);

        if(!result)
            return ResponseResult.error("清零房间数失败!");

        result = branchService.removeById(branchId);

        if(result)
            return ResponseResult.success();
        else
            return ResponseResult.error("删除分店失败!");

    }

    /**
     * 修改分店
     * /api/branch/update
     */
    @Operation(summary = "修改分店")
    @PutMapping("/update")
    public ResponseResult updateBranch(@RequestBody Branch branch) throws Exception {

        boolean result = branchService.updateById(branch);

        if(result)
            return ResponseResult.success();
        else
            return ResponseResult.error("修改分店失败!");

    }

    /**
     * 根据分店编号获取分店信息
     * /api/branch/{branchId}
     */
    @SentinelResource(value="getBranchById",blockHandler = "getDefaultBranch",fallback="getFallBackBranch",exceptionsToIgnore = NullPointerException.class)
    @Operation(summary = "根据分店编号获取分店信息")
    @GetMapping("/{branchId}")
    public ResponseResult getBranchById(@PathVariable Integer branchId) throws Exception {

        if(branchId>10000)
            throw new NullPointerException("类别编号不能大于10000！");

//        log.info("timeout: " + workProperties.getTimeout());
//        log.info("location: " + workProperties.getLocation());
//        log.info("typeMode: " + workProperties.getTypeMode());

        Branch branch = branchService.getById(branchId);

        ResponseResult data = roomFeignClient.getRoomListByBranchId(branchId);
        List<Room> roomList = (List<Room>) data.getData();
        branch.setRoomList(roomList);

        if (branch != null){
            ResponseResult result = ResponseResult.success(branch);
            result.setMsg("获取分店信息成功！分店评级：" + rank + "，分店容量：" + capacity);
            return result;
        }
        else
            return ResponseResult.error("获取分店信息失败!");
    }

    //被拦截时所运行的方法
    public ResponseResult getDefaultBranch(@PathVariable Integer branchId,BlockException e) throws Exception{

        Branch defaultBranch=new Branch();
        defaultBranch.setBranchId(branchId);
        defaultBranch.setBranchName("默认分店");

        ResponseResult result=ResponseResult.success(defaultBranch);
        result.setMsg("默认分店加载完成！"+e.getMessage());
        return result;
    }

    //出现异常是所运行的方法
    public ResponseResult getFallBackBranch(@PathVariable Integer branchId, Throwable e) throws Exception{

        Branch defaultBranch=new Branch();
        defaultBranch.setBranchId(branchId);
        defaultBranch.setBranchName("默认分店");

        ResponseResult result=ResponseResult.success(defaultBranch);
        result.setMsg("分店加载失败，返回默认值！"+e.getMessage());
        return result;


    }

    /**
     * 获取分店编号，需要指定分店名
     * /api/branch/branchId/branchName/{branchName}
     */
    @SentinelResource(value="getBranchIdByBranchName")
    @Operation(summary = "根据分店名获取分店编号")
    @GetMapping("/branchId/branchName/{branchName}")
    public Integer getBranchIdByBranchName(@PathVariable String branchName) throws Exception {

//        log.info("开始加载房间数据...");
//        Thread.sleep(10000);
//        log.info("......ok!");

        return branchService.getBranchIdByBranchName(branchName);

    }

    @SentinelResource(value = "loadBranchList")
    @Operation(summary = "分店分页")
    @GetMapping("list")  /// api/branch/list?pageNo=
    public ResponseResult loadPagedList(int pageNo) throws Exception{

        Page<Branch> page = branchService.loadPagedBranch(pageNo, 10);

        if(page!=null)
            return ResponseResult.success(page);
        else
            return ResponseResult.error("查询分店分页信息失败!");
    }

}
