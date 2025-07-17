package com.abcd.branch.service.impl;

import com.abcd.branch.service.BranchService;
import com.abcd.hotel.domain.Branch;
import com.abcd.hotel.mapper.BranchMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service  /// 服务
public class BranchServiceImpl extends ServiceImpl<BranchMapper, Branch> implements BranchService {

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer getBranchIdByBranchName(String branchName) {
        LambdaQueryWrapper<Branch> wrapper = Wrappers.lambdaQuery();
        wrapper.select(Branch::getBranchId)  // 只查询id字段
                .eq(Branch::getBranchName, branchName)  // 根据name字段匹配
                .last("LIMIT 1");  // 限制只返回一条

        Branch branch = getOne(wrapper);
        return branch != null ? branch.getBranchId() : null;
    }

    @Transactional
    @Override
    public Page<Branch> loadPagedBranch(int pageNo, int pageSize) {

        Page<Branch> page = new Page<>(pageNo, pageSize);
        return this.page(page);

    }

    @Override
    public boolean clearRoomCount(Integer branchId) {

        UpdateWrapper<Branch> wrapper = new UpdateWrapper<>();
        wrapper.setSql("room_count = 0")
                .eq("branch_id", branchId);

        return this.update(wrapper);

    }

    @Override
    public boolean decRoomCount(Integer branchId) {

        UpdateWrapper<Branch> wrapper = new UpdateWrapper<>();
        wrapper.setSql("room_count = room_count - 1")
                .eq("branch_id", branchId);

        return this.update(wrapper);

    }

}
