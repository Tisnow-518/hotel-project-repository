package com.abcd.branch;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = {"com.abcd.branch", "com.abcd.hotel.config","com.abcd.hotel.utils","com.abcd.hotel.controller"})  /// SpringBoot微服务
@EnableDiscoveryClient /// 发现微服务
@EnableTransactionManagement/// 启用事务管理
@MapperScan("com.abcd.hotel.mapper")  /// Mapper扫描路径
@EnableFeignClients  /// OpenFeign相关
public class BranchMainApplication {

    public static void main(String[] args) {

        SpringApplication.run(BranchMainApplication.class, args);

    }

}
