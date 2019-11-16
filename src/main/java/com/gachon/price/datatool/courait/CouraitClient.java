package com.gachon.price.datatool.courait;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "courait", url = "http://52.78.16.243:7001/history")
public interface CouraitClient {

    @GetMapping("/all")
    CouraitResponse getAllPurchaseList();

    @GetMapping("/userlist")
    CouraitResponse getUserPurchaseList(@RequestParam("email") String email);

}
