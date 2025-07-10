package com.trimblecars.carleaseservice.config;

import com.trimblecars.carleaseservice.util.MapperUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public MapperUtil mapperUtil(){
        return  new MapperUtil();
    }
}
