package com.webApp.config;

import com.webApp.mainPack.Option;
import com.webApp.mainPack.Task;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    @Bean
    public Option option() {
        return new Option();
    }
    @Bean
    public Task task() {

    }
}
