package com.abcd.domain;

import lombok.Data;

@Data
public class Branch {

    private Integer branchId;
    private String branchName;
    private String branchAddress;
    private String branchPhone;
    private Integer roomCount;
    private String branchPicUrl;

}
