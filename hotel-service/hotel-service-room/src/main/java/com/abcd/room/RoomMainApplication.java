package com.abcd.room;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = {"com.abcd.room", "com.abcd.hotel.config","com.abcd.hotel.utils"})  /// SpringBoot微服务
@EnableDiscoveryClient  /// 发现微服务
@MapperScan("com.abcd.hotel.mapper")  /// Mapper扫描路径
@EnableFeignClients
public class RoomMainApplication {

    public static void main(String[] args) {

        SpringApplication.run(RoomMainApplication.class, args);

    }

}
