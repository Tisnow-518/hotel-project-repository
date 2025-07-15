package com.abcd.branch;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"com.abcd.branch", "com.abcd.hotel.config"})
@MapperScan("com.abcd.hotel.mapper")
public class BranchMainApplication {

    public static void main(String[] args) {

        SpringApplication.run(BranchMainApplication.class, args);

    }

}
