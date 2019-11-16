package com.gachon.price.datatool;

import com.gachon.price.datatool.courait.CouraitClient;
import com.gachon.price.datatool.naver.NaverClient;
import feign.Feign;
import feign.Retryer;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@EnableFeignClients
@Configuration
public class DataToolConfig {

    @Bean
    public CouraitClient couraitClient() {
        return Feign.builder()
                    .contract(new SpringMvcContract())
                    .retryer(Retryer.NEVER_RETRY)
                    .target(CouraitClient.class, "http://52.78.16.243:7001/history");
    }

    @Bean
    public NaverClient naverClient() {
        return Feign.builder()
                    .contract(new SpringMvcContract())
                    .retryer(Retryer.NEVER_RETRY)
                    .target(NaverClient.class, "https://openapi.naver.com");
    }

}
