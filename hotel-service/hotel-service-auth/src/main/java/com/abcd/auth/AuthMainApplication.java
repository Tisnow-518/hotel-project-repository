package com.abcd.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication  /// SpringBoot微服务
@EnableDiscoveryClient  /// 发现微服务
public class AuthMainApplication {

    public static void main(String[] args) {

        SpringApplication.run(AuthMainApplication.class, args);

    }

}
