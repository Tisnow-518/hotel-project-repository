package com.abcd.room.Feign;

import com.abcd.hotel.utils.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "exchange-client", url = "https://api.tanshuapi.com/api/exchange/v1/index2")
public interface MoneyExchangeFeignClient {

    @GetMapping
    ResponseResult getExchange(@RequestParam String key,
                               @RequestParam String from,
                               @RequestParam String to,
                               @RequestParam String money);

}
