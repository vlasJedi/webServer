package com.webApp.repos.RouteRepo;

import com.webApp.repos.PersistenceManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.*;

public class RouteService {
    /*public enum CATEGORIES {
        TIMES("times"),
        ADJECTIVES("adjectives"),
        MATCHING("matching");
        private String category;
        CATEGORIES(String category) {
            this.category = category;
        }
        public String toString() {
            return this.category;
        }

        public String getCategory() {
            return this.category;
        }
    }*/
    private static RouteService routeService = null;

    private RouteService() {
        Set<Route> routes = new HashSet<Route>();
        routes.add(new Route("/home"));
        routes.add(new Route("/about-us"));
        routes.add(new Route("/portfolio"));
        routes.add(new Route("/contact"));
        insertRoutes(routes);
    }

    public boolean insertRoute(Route route) {
        if (route == null) {
            System.out.println("RouteService:insertRoute > Null route is not allowed");
            return false;
        }
        EntityManager em = PersistenceManager.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(route);
        em.flush();
        transaction.commit();
        em.close();
        return true;
    }

    /*public static Set<Route> createCategoriesSet() {
        Set<Route> set = new HashSet<>();
        Arrays.stream(CATEGORIES.values()).forEach(
                category -> set.add(new Route(category.toString(), "description for: " + category)));
        return set;
    }*/

    /*public static void populateCategoriesTable() {
        Set<Route> set = createCategoriesSet();
        insertCategories(set);
    }*/

    public boolean insertRoutes(Set<Route> routes) {
        EntityManager em = PersistenceManager.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        routes.iterator().forEachRemaining(em::persist);
        em.flush();
        transaction.commit();
        em.close();
        return true;
    }

    public Set<Route> getAllRoutes() {
        EntityManager em = PersistenceManager.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Set<Route> routes = new HashSet<>(em.createQuery(
                "SELECT route FROM Route route", Route.class).getResultList());
        em.flush();
        transaction.commit();
        em.close();
        return routes;
    }

    public static RouteService get() {
        if (routeService == null) routeService = new RouteService();
        return routeService;
    }
}
