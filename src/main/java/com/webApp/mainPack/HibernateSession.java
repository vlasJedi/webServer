package com.webApp.mainPack;

public class HibernateSession {
    /*private static SessionFactory sessionFactoryInstance;
    private static SessionFactory createSessionFactory() {
        Configuration config = new Configuration()
                // will automatically search for such classes
                .addAnnotatedClass(Option.class)
                .addAnnotatedClass(Task.class)
                .setProperty("hibernate.dialect", "")
                .setProperty("hibernate.hbm2ddl.auto", "update")
                .setProperty("hibernate.show_sql", "true")
                .setProperty("hibernate.format_sql", "true")
                .setProperty("hibernate.connection.driver_class", "org.postgresql.Driver")
                .setProperty("hibernate.connection.url", "jdbc:postgresql://localhost/postgres")
                .setProperty("hibernate.connection.username", "postgres")
                .setProperty("javax.persistence.schema-generation.database.action", "drop-and-create");
        return config.buildSessionFactory();
    }
    public static Session getSession() {
        if (sessionFactoryInstance != null) {
            return sessionFactoryInstance.openSession();
        }
        sessionFactoryInstance = createSessionFactory();
        return sessionFactoryInstance.openSession();
    }*/
}
