package com.abcd.hotel.utils;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component   //自定义一个MetaObjectHandler的实现类，注入到容器中，就会被MP自动识别，并使用
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        metaObject.setValue("createTime", new Date());
        metaObject.setValue("updateTime", new Date());
        metaObject.setValue("createdBy", "000001");
        metaObject.setValue("updatedBy", "000001");
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        metaObject.setValue("updateTime", new Date());
        metaObject.setValue("updatedBy", "000001");
    }

}
