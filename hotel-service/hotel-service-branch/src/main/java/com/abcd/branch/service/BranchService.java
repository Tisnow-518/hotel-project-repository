package com.abcd.branch.service;

import com.abcd.hotel.domain.Branch;
import com.abcd.hotel.utils.ResponseResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface BranchService extends IService<Branch> {

    Integer getBranchIdByBranchName(String branchName);

    Page<Branch> loadPagedBranch(int pageNo, int pageSize);

    /**
     * 将房间数清零
     */
    boolean clearRoomCount(Integer branchId);

}
