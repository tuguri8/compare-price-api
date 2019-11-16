package com.gachon.price.controller;

import com.gachon.price.service.PriceCompareResponse;
import com.gachon.price.service.PriceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api")
public class PriceController {

    private final PriceService priceService;

    public PriceController(PriceService priceService) {this.priceService = priceService;}

    @GetMapping("list")
    public List<PriceCompareResponse> getPriceCompare(@RequestParam("email") String email) {
        return priceService.getPriceCompare(email);
    }

}
