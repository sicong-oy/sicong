package com.qf.shopback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan(basePackages = "com.qf")
@EnableEurekaClient
@EnableFeignClients(basePackages = "com.qf.feign.api")
public class ShopBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopBackApplication.class, args);
	}

}
