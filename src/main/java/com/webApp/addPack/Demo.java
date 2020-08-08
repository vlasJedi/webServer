package com.webApp.addPack;
import com.webApp.repos.RouteRepo.Route;
import com.webApp.repos.RouteRepo.RouteService;
import com.webApp.repos.PersistenceManager;
import com.webApp.server.AppServer;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

public class Demo {
    public static void main(String[] args) {
        PersistenceManager.open();
        // this reads config.xml to establish context
        //ApplicationContext context = new ClassPathXmlApplicationContext("spring-init-bean.xml");
        // this annotation based
        //AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        //Session session = HibernateSession.getSession();
        //Transaction transaction = session.getTransaction();
        //transaction.begin();
        // scan for beans
        //context.scan("com.webApp");
        // +need to clear all already runnable instances
        //context.refresh();

        AppServer.start();
        RouteService routeService = RouteService.get();
        //Set<Route> categories = RouteService.getAllCategories();
        //categories.iterator().forEachRemaining(category -> category.toJson());


        //EntityManager em = PersistenceManager.getEntityManager();
        //EntityTransaction transaction = em.getTransaction();
        //transaction.begin();
        //Task task = new Task("Task1", "test task from main method");
        //EntityActionable option = context.getBean("option", EntityActionable.class);
        //Option option = new Option();
        /*clearTable(Option.class, entityManager);
        clearTable(Task.class, entityManager);*/
        //option.setName("Loco");
        //option.setTask(task);
        //em.persist(task);
        //em.persist(option);
        // CRUD operations
        //entityManager.persist(option); // INSERT
        //entityManager.persist(task); // INSERT
        // this is read from db by find method
        //Option option1 = entityManager.find(Option.class, 1); // SELECT
        //System.out.println("Value from DB: " + option1.getValue());
        //System.out.println("List[0] element: " + options.get(0).getValue());
        // sync pers context and sends all current queries to be executed, but last word on .commit()
        //em.flush();
        //transaction.commit();
        //session.close();
        //em.close();
        //PersistenceManager.close();
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
