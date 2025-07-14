package com.abcd.branch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class BranchMainApplication {

    public static void main(String[] args) {

        SpringApplication.run(BranchMainApplication.class, args);

    }

}
