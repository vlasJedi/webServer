package com.webApp.addPack;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import com.webApp.mainPack.Console;
public class Demo {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-init-bean.xml");
        Console console = (Console) context.getBean("console");
        console.logMessage();
    }
}
