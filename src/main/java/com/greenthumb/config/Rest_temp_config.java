package com.greenthumb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Rest_temp_config {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
