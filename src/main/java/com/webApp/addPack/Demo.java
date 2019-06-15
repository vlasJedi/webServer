package com.webApp.addPack;
import com.webApp.mainPack.Task;
import com.webApp.mainPack.Option;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-init-bean.xml");
        /*
        EntityManagerFactory - Interface used to interact with the entity manager factory for the persistence unit.
        When the application has finished using the entity manager factory, and/or at application shutdown,
        the application should close the entity manager factory. Once an EntityManagerFactory has been closed,
        all its entity managers are considered to be in the closed state.
        @param persistenceUnitName - name from config file for the unit*/
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("postgres");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        //Task task = (Task) context.getBean("task");
        //List<Option> options = (List<Option>) context.getBean("options");
        Option option = new Option();
        option.setName("1");
        option.setValue("someWord1");
        entityManager.persist(option);
        transaction.commit();
        entityManager.close();
        entityManagerFactory.close();
        //options.add(option);
        //task.setName("firstTask");
        //task.setOptions(options);
        //System.out.println(task.getName());
    }
}
