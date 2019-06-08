package com.webApp.addPack;
import com.webApp.mainPack.Task;
import com.webApp.mainPack.Option;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class Demo {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-init-bean.xml");
        Task task = (Task) context.getBean("task");
        List<Option> options = (List<Option>) context.getBean("options");
        Option option = new Option();
        option.setName("1");
        option.setValue("someWord1");
        option.setSelected(true);
        options.add(option);
        task.setName("firstTask");
        task.setOptions(options);
        System.out.println(task.getName());
    }
}
