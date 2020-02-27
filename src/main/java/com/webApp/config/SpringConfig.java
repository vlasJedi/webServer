package com.webApp.config;

import com.webApp.repos.OptionRepo.Option;
import com.webApp.repos.TaskRepo.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;

@Configuration
// searches in resources folder
@PropertySource("classpath:BeansProps.properties")
public class SpringConfig {

    @Autowired
    private Environment env;

    // the name used by @qualifier to distinguish Classes with same same implementation
    @Bean(name="option")
    @Scope("prototype")
    public Option option() {
        // dep injection based on setters
        // read from .properties file config
        String name = env.getProperty("option.name");
        String value = env.getProperty("option.value");
        Option option = new Option();
        option.setName(name);
        option.setValue(value);
        return option;
    }
    @Bean(name="task")
    @Scope("prototype")
    public Task task() {
        return new Task("defaultName", null);
    }
}
