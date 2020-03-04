package com.webApp.repos;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceManager {
    private static EntityManagerFactory emf;
    public static EntityManager getEntityManager() {
        /*EntityManagerFactory - Interface used to interact with the entity manager factory for the persistence unit.
                When the application has finished using the entity manager factory, and/or at application shutdown,
                the application should close the entity manager factory. Once an EntityManagerFactory has been closed,
        all its entity managers are considered to be in the closed state.
        @param persistenceUnitName - name from config file for the unit*/
        if (emf != null) return emf.createEntityManager();
        emf = Persistence.createEntityManagerFactory("appData");
        // represents the application session or dialog with the database
        return emf.createEntityManager();
    }
    public static void close() {
        if (emf != null) {
            emf.close();
        }
    }
}
