package com.abcd.branch.config;

import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Configuration
@Slf4j
public class ServiceConfig {

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {

        return new RestTemplate();

    }

    @Bean
    public ApplicationRunner getApplicationRunner(NacosConfigManager nacosConfigManager) {

        log.info("create ApplicationRunner");

        return new ApplicationRunner() {

            @Override
            public void run(ApplicationArguments args) throws Exception {

                ConfigService configService = nacosConfigManager.getConfigService();
                configService.addListener("work.properties", "DEFAULT_GROUP", new Listener() {
                    @Override
                    public Executor getExecutor() {
                        return Executors.newSingleThreadExecutor();
                    }

                    @Override
                    public void receiveConfigInfo(String s) {
                        System.out.println("更新后的配置:" + s);
                        System.out.println("群发短信，通知配置发生变化!");
                    }
                });

            }

        };

    }

}
