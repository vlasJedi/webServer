package com.webApp.repos.CategoryRepo;

import com.webApp.repos.PersistenceManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TransactionRequiredException;
import java.util.*;
import java.util.stream.Stream;

public class CategoryService {
    public enum CATEGORIES {
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
    }

    public static boolean insertCategory(Category category) {
        if (category == null) {
            System.out.println("CategoryService:insertCategory > Null category is not allowed");
            return false;
        }
        EntityManager em = PersistenceManager.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(category);
        em.flush();
        transaction.commit();
        em.close();
        return true;
    }

    public static Set<Category> createCategoriesSet() {
        Set<Category> set = new HashSet<>();
        Arrays.stream(CATEGORIES.values()).forEach(
                category -> set.add(new Category(category.toString(), "description for: " + category)));
        return set;
    }

    public static void populateCategoriesTable() {
        Set<Category> set = createCategoriesSet();
        insertCategories(set);
    }

    public static boolean insertCategories(Set<Category> categories) {
        EntityManager em = PersistenceManager.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        categories.iterator().forEachRemaining(em::persist);
        em.flush();
        transaction.commit();
        em.close();
        return true;
    }

    public static Set<Category> getAllCategories() {
        EntityManager em = PersistenceManager.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Set<Category> categories = new HashSet<>(em.createQuery(
                "SELECT category FROM Category category", Category.class).getResultList());
        em.flush();
        transaction.commit();
        em.close();
        return categories;
    }
}
