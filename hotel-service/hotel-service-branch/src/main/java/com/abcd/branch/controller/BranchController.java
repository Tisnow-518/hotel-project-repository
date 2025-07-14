package com.abcd.branch.controller;

import com.abcd.branch.service.BranchService;
import com.abcd.hotel.domain.Branch;
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

}
