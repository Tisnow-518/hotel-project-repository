package com.abcd.branch.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class IDInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header("userid", "abc123");
    }

}
