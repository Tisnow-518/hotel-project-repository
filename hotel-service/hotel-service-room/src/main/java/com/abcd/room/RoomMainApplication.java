package com.abcd.room;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.abcd.hotel.mapper")
public class RoomMainApplication {

    public static void main(String[] args) {

        SpringApplication.run(RoomMainApplication.class, args);

    }

}
