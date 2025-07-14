package com.abcd.hotel.utils;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 通用返回结果，服务端响应的数据最终都会封装成此对象
 * @param <T>
 */
@Data
public class ResponseResult<T> {

    private Integer code; //编码：1成功，0和其它数字为失败

    private String msg; //错误信息

    private T data; //数据

    private Map map = new HashMap(); //动态数据

    public static <T> ResponseResult<T> success() {
        ResponseResult<T> r = new ResponseResult<T>();
        r.code = 1;
        return r;
    }

    public static <T> ResponseResult<T> success(T object) {
        ResponseResult<T> r = new ResponseResult<T>();
        r.data = object;
        r.code = 1;
        return r;
    }

    public static <T> ResponseResult<T> error(String msg) {
        ResponseResult r = new ResponseResult();
        r.msg = msg;
        r.code = 0;
        return r;
    }

    public ResponseResult<T> add(String key, Object value) {
        this.map.put(key, value);
        return this;
    }

}
