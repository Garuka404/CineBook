package com.cbs.cinebook.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Confugaration {
    
    @Bean
    public ModelMapper mapper(){
        return new ModelMapper();
    }
}
