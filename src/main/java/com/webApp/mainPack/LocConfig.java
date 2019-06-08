package com.webApp.mainPack;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;


/*The @Configuration annotation serves as a clue to Spring that this class will contain
one or more Spring bean declarations. Those bean declarations are just methods that
are annotated with @Bean*/
@Configuration
public class LocConfig {
    @Bean
    public Option option() {
        return new Option();
    }
    @Bean Task task() {
        Task task = new Task();
        List<Option> options = options();
        task.setOptions(options);
        return task;
    }
    @Bean List<Option> options() {
        return new ArrayList<Option>();
    }
}
