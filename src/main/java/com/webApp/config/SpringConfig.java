package com.webApp.config;

import com.webApp.mainPack.Option;
import com.webApp.mainPack.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.util.List;

@Configuration
@PropertySource("classpath:com/webApp/mainPack/BeansProps.properties")
public class SpringConfig {

    @Autowired
    private Environment env;

    @Bean(name="option")
    public Option option() {
        // dep injection based on setters
        String name = env.getProperty("option.name");
        String value = env.getProperty("option.value");
        Option option = new Option();
        option.setName(name);
        option.setValue(value);
        return new Option();
    }
    @Bean(name="task")
    public Task task(@Qualifier("option") List<Option> options) {
        return new Task(options);
    }
}
