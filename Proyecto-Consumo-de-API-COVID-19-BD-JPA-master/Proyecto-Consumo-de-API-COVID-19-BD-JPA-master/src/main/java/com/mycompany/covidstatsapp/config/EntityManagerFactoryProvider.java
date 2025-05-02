package com.mycompany.covidstatsapp.config;

import java.io.IOException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class EntityManagerFactoryProvider {

    private static final EntityManagerFactory factory = buildEntityManagerFactory();

    private static EntityManagerFactory buildEntityManagerFactory() {
        Map<String, String> properties = new HashMap<>();

        
        Properties props = new Properties();
        try {
       
            props.load(EntityManagerFactoryProvider.class.getClassLoader()
                    .getResourceAsStream("application.properties"));
        } catch (IOException e) {
        }

      
        properties.put("jakarta.persistence.jdbc.url", props.getProperty("db.url"));
        properties.put("jakarta.persistence.jdbc.user", props.getProperty("db.user"));
        properties.put("jakarta.persistence.jdbc.password", props.getProperty("db.password"));
        properties.put("jakarta.persistence.jdbc.driver", "com.mysql.cj.jdbc.Driver");
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");

        
        return Persistence.createEntityManagerFactory("covidstatsPU", properties);
    }

    public static EntityManager getEntityManager() {
        return factory.createEntityManager();
    }
}