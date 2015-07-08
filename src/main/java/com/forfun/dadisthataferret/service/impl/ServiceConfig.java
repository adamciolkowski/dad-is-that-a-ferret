package com.forfun.dadisthataferret.service.impl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@Configuration
public class ServiceConfig {

    @Bean
    public Resource animalsResource() {
        return new ClassPathResource("/animals.txt");
    }
}
