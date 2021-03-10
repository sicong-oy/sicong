package com.qf.config;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBatsPlusConfig {

    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return  new PaginationInterceptor() ;
    }
}
