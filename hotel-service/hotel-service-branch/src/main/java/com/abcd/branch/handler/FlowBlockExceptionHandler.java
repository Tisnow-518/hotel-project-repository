package com.abcd.branch.handler;

import com.abcd.hotel.utils.ResponseResult;
import com.alibaba.csp.sentinel.adapter.spring.webmvc_v6x.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import java.io.PrintWriter;
//自定义一个BlockException处理类 网址异常不交给sentinel处理异常


@Component
public class FlowBlockExceptionHandler implements BlockExceptionHandler {

    private ObjectMapper objectMapper=new ObjectMapper();

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, String s, BlockException e) throws Exception {

        httpServletResponse.setContentType("application/json;charset=utf-8");
        PrintWriter writer = httpServletResponse.getWriter();

        ResponseResult<Object> result = ResponseResult.error("访问受限，因为访问频率过高，请稍后再试！");
        objectMapper.writeValue(writer,result);


    }

}
