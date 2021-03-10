package com.qf.shopitem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan(basePackages = "com.qf")
public class ShopItemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopItemApplication.class, args);
    }

}
