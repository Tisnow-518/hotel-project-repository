package com.abcd.branch.controller;

import com.abcd.hotel.domain.Branch;
import com.abcd.branch.service.BranchService;
import com.abcd.hotel.utils.ResponseResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/branch")
@Slf4j
public class BranchController {

    @Autowired
    private BranchService branchService;

    @PostMapping("/save")
    public String create(@RequestBody Branch branch) throws Exception {

        log.info("create branch, branch info: "+branch);

        branchService.save(branch);

        return null;

    }

    @GetMapping("list")   ///api/goods/list?pageNo=1
    public ResponseResult loadPagedList(int pageNo) throws Exception{
        //页面规模默认为8条
//        System.out.println("load goods ,pageNo:"+pageNo);

        Page<Branch> page = branchService.loadPagedBranch(pageNo, 8);

        if(page!=null)
            return ResponseResult.success(page);
        else
            return ResponseResult.error("查询分店分页信息失败!");
    }

}
