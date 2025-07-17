package com.abcd.branch.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "work")   /// 绑定配置属性
@Data
public class WorkProperties {

    private Integer timeout;
    private String location;
    private String typeMode;

}
