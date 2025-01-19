package com.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaUtil {
    // Una fábrica de EntityManager, equivalente a la SessionFactory en Hibernate.
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY =
            // Se crea la fábrica utilizando la unidad de persistencia definida en persistence.xml.
            Persistence.createEntityManagerFactory("hibernate-clase-jpa");

    // Metodo que devuelve una nueva instancia de EntityManager, equivalente a una Session en Hibernate.
    public static EntityManager getEntityManager() {
        return ENTITY_MANAGER_FACTORY.createEntityManager(); // Crea y retorna un EntityManager.
    }
}