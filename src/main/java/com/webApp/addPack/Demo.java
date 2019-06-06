package com.webApp.addPack;
import com.webApp.mainPack.Task;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Demo {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-init-bean.xml");
        Task task = (Task) context.getBean("task");
        System.out.println(task.getName());
    }
}
