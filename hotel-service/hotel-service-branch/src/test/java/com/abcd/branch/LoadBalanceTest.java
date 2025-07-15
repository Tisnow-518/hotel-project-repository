package com.abcd.branch;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;

@SpringBootTest
public class LoadBalanceTest {

    @Autowired
    DiscoveryClient discoveryClient;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Test
    public void balanceTest() {

        ServiceInstance instance = loadBalancerClient.choose("hotel-service-room");
        System.out.println(instance.getHost() + ":" + instance.getPort());
        instance = loadBalancerClient.choose("hotel-service-room");
        System.out.println(instance.getHost() + ":" + instance.getPort());
        instance = loadBalancerClient.choose("hotel-service-room");
        System.out.println(instance.getHost() + ":" + instance.getPort());
        instance = loadBalancerClient.choose("hotel-service-room");
        System.out.println(instance.getHost() + ":" + instance.getPort());
        instance = loadBalancerClient.choose("hotel-service-room");
        System.out.println(instance.getHost() + ":" + instance.getPort());
        instance = loadBalancerClient.choose("hotel-service-room");
        System.out.println(instance.getHost() + ":" + instance.getPort());

    }

}
