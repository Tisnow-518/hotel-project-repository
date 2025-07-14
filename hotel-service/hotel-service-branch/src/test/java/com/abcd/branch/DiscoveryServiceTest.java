package com.abcd.branch;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

@SpringBootTest
public class DiscoveryServiceTest {

    @Autowired
    DiscoveryClient discoveryClient;

    @Test
    void testDiscoveryService(){
        for (String service : discoveryClient.getServices()) {
            System.out.println(service);
            for (ServiceInstance instance : discoveryClient.getInstances(service)) {
                System.out.println(instance.getUri()+", "+instance.getHost()+":"+instance.getPort());
            }
        }
    }

}
