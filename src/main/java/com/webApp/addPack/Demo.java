package com.webApp.addPack;
import com.sun.net.httpserver.HttpServer;
import com.webApp.mainPack.Task;
import com.webApp.mainPack.Option;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.persistence.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-init-bean.xml");
       /* Runnable server = new RunnableServer();
        Thread serverThread = new Thread(server);
        serverThread.start();*/
        InetAddress address;
        try {
            address = InetAddress.getByName("localhost");
            System.out.println(address.getHostName());
            InetSocketAddress isa = new InetSocketAddress(address.getHostName(), 8080);
            System.out.println(isa.getHostName());
            HttpServer httpServer = HttpServer.create(isa,0);
            httpServer.createContext("/", new RootHandler());
            httpServer.setExecutor(null); // creates a default executor
            httpServer.start();
        } catch (UnknownHostException e) {
            System.out.println("Could not resolve ip/hostname");
        } catch (IOException e) {
            System.out.println("Could not IOException");
        }

        /*
        EntityManagerFactory - Interface used to interact with the entity manager factory for the persistence unit.
        When the application has finished using the entity manager factory, and/or at application shutdown,
        the application should close the entity manager factory. Once an EntityManagerFactory has been closed,
        all its entity managers are considered to be in the closed state.
        @param persistenceUnitName - name from config file for the unit*/
        /*EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("postgres");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.flush();
        //Task task = (Task) context.getBean("task");
        //List<Option> options = (List<Option>) context.getBean("options");
        Option option = new Option();
        option.setName("1");
        option.setValue("someWord1");
        entityManager.persist(option);
        Option option1 = entityManager.find(Option.class, 1);
        System.out.println("Value from DB: " + option1.getValue());
        String queryStringSelectAll = "SELECT a FROM Option a ";
        TypedQuery<Option> querySelectAllFromOptions = entityManager.createQuery(queryStringSelectAll, Option.class);
        List<Option> options = querySelectAllFromOptions.getResultList();
        System.out.println("List[0] element: " + options.get(0).getValue());
        transaction.commit();
        entityManager.close();
        entityManagerFactory.close();*/
        //options.add(option);
        //task.setName("firstTask");
        //task.setOptions(options);
        //System.out.println(task.getName());
    }
}
