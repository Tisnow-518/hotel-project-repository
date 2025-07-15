package com.abcd.branch.service.impl;

import com.abcd.branch.service.BranchService;
import com.abcd.hotel.domain.Branch;
import com.abcd.hotel.mapper.BranchMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class BranchServiceImpl extends ServiceImpl<BranchMapper, Branch> implements BranchService {

    @Override
    public Page<Branch> loadPagedBranch(int pageNo, int pageSize) {

        Page<Branch> page = new Page<>(pageNo, pageSize);
        return this.page(page);

    }

}
