package com.webApp.repos.UserRepo;

import com.webApp.repos.PersistenceManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class UsersService {
    public static boolean createUser(User user) {
        System.out.println("UsersService:createUser verify user existence");
        if (readUser(user.getName()) != null) {
            System.out.println("UsersService:createUser user already exist");
            return false;
        }
        EntityManager em = PersistenceManager.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(user);
        em.flush();
        transaction.commit();
        em.close();
        System.out.println("UsersService:createUser new user successfully created");
        return true;
    }

    public static User readUser(String userName) {
        if (userName == null || userName.isEmpty()) {
            return null;
        }
        EntityManager em = PersistenceManager.getEntityManager();
        User user = em.find(User.class, userName);
        em.close();
        return user;
    }

    public static boolean isValidUser(User user) {
        if (user == null) {
            return false;
        }
        if (user.getName() == null || user.getName().isEmpty() || user.getPassword() == null || user.getPassword().isEmpty()
            || user.getMail() == null || user.getMail().isEmpty()) {
            return false;
        }
        return true;
    }
}
