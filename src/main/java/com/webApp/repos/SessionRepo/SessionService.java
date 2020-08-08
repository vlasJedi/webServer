package com.webApp.repos.SessionRepo;

import com.webApp.repos.PersistenceManager;
import com.webApp.repos.UserRepo.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class SessionService {
    public static String createSession(String username) {
        System.out.printf("SessionService:createSession for the user %s existence", username);
        if (username == null || username.isEmpty()) {
            System.out.println("SessionService:createSession user is null");
            return null;
        }
        /*em.persist(user)
        EntityManager em = PersistenceManager.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(user);
        em.flush();
        transaction.commit();
        em.close();
        System.out.println("UsersService:createUser new user successfully created");
        return true;*/
        return null;
    }
}
