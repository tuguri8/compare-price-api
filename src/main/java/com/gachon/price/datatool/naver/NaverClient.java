package com.gachon.price.datatool.naver;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "naver", url = "https://openapi.naver.com")
public interface NaverClient {
    @GetMapping("v1/search/shop.json")
    NaverShoppingResponse getItemPrice(@RequestHeader(value = "X-Naver-Client-Id") String id,
                                       @RequestHeader(value = "X-Naver-Client-Secret") String secret,
                                       @RequestParam("query") String query);
}
