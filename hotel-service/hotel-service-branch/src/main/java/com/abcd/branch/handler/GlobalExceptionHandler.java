package com.abcd.branch.handler;

import com.abcd.hotel.utils.ResponseResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
//全局异常处理器
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseResult processException(Exception e) throws Exception {
        return ResponseResult.error("系统发生错误，请稍后访问！");
    }
}
