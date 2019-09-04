package com.webApp.addPack;
import com.fasterxml.classmate.AnnotationConfiguration;
import com.fasterxml.classmate.AnnotationOverrides;
import com.sun.net.httpserver.HttpServer;
import com.webApp.config.SpringConfig;
import com.webApp.mainPack.EntityActionable;
import com.webApp.mainPack.HibernateSession;
import com.webApp.mainPack.Option;
import com.webApp.mainPack.Task;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.annotation.AnnotationConfigurationException;

import javax.persistence.*;
import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        // this reads config.xml to establish context
        //ApplicationContext context = new ClassPathXmlApplicationContext("spring-init-bean.xml");
        // this annotation based
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        Session session = HibernateSession.getSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        // scan for beans
        //context.scan("com.webApp");
        // need to clear all already runnable instances
        //context.refresh();
        /* Runnable server = new RunnableServer();
        Thread serverThread = new Thread(server);
        serverThread.start();*/
        /*InetAddress address;
        try {
            address = InetAddress.getByName("localhost");
            InetSocketAddress isa = new InetSocketAddress(address.getHostName(), 8080);
            HttpServer httpServer = HttpServer.create(isa,0);
            httpServer.createContext("/", new RootHandler());
            httpServer.setExecutor(null); // creates a default executor
            httpServer.start();
        } catch (UnknownHostException e) {
            System.out.println("Could not resolve ip/hostname");
        } catch (IOException e) {
            System.out.println("Could not IOException");
        }*/

        /*
        EntityManagerFactory - Interface used to interact with the entity manager factory for the persistence unit.
        When the application has finished using the entity manager factory, and/or at application shutdown,
        the application should close the entity manager factory. Once an EntityManagerFactory has been closed,
        all its entity managers are considered to be in the closed state.
        @param persistenceUnitName - name from config file for the unit*/

        /*EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("postgres");
        // represents the application session or dialog with the database
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();*/
        Task task = context.getBean("task", Task.class);
        task.setName("Task1");
        Option option = context.getBean("option", Option.class);
        /*clearTable(Option.class, entityManager);
        clearTable(Task.class, entityManager);*/
        option.setName("Loco");
        option.setTask(task);
        session.save(task);
        session.save(option);
        // CRUD operations
        //entityManager.persist(option); // INSERT
        //entityManager.persist(task); // INSERT
        // this is read from db by find method
        //Option option1 = entityManager.find(Option.class, 1); // SELECT
        //System.out.println("Value from DB: " + option1.getValue());
        //System.out.println("List[0] element: " + options.get(0).getValue());
        // sync pers context and sends all current queries to be executed, but last word on .commit()
        //entityManager.flush();
        transaction.commit();
        session.close();
        //entityManager.close();
        //entityManagerFactory.close();
        //options.add(option);
        //task.setName("firstTask");
        //task.setOptions(options);
        //System.out.println(task.getName());
    }
    // Class<T> - represents class of object with type T, to get such need Class.class
    public static <T> boolean clearTable(Class<T> entityClass, EntityManager entityManager) {
        int primaryKey;
        T entityFromDb = null;
        List<T> rows = getRows(entityClass, entityManager);
        if ( rows == null ) {
            System.out.println("** clearTable: no rows to clear !");
            return false;
        }
        // while ( (entityFromDb = entityManager.find(entityClass, primaryKey)) != null ) {
        rows.forEach( (row) -> {
            entityManager.remove(row);
            System.out.println("main:clearTable: Removed entity: " + row);
        });
        System.out.println("** main:clearTable: Finished");
        return true;
    }
    public static <T> List<T> getRows(Class<T> entityClass, EntityManager entityManager) {
        // create query
        String queryStringSelectAll = "SELECT o FROM Option o ";
        TypedQuery<T> querySelectAllFromOptions = entityManager.createQuery(queryStringSelectAll, entityClass);
        List<T> rows = querySelectAllFromOptions.getResultList();
        return rows;
    }
}
