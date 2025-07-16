package com.abcd.auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = {"com.abcd.auth", "com.abcd.hotel.config","com.abcd.hotel.utils"})  /// SpringBoot微服务
@EnableDiscoveryClient  /// 发现微服务
@MapperScan("com.abcd.hotel.mapper")  /// Mapper扫描路径
public class AuthMainApplication {

    public static void main(String[] args) {

        SpringApplication.run(AuthMainApplication.class, args);

    }

}
