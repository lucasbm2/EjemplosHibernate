package com.example;

import com.example.model.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Test;

public class JPATest {

    @Test
    void persist() {
        EntityManager entityManager = JpaUtil.getEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Employee employee1 = new Employee("employeeJPA1", 43);
        Employee employee2 = new Employee("employeeJPA2", 45);

        entityManager.persist(employee1);
        entityManager.persist(employee2);

        transaction.commit();

        entityManager.close();
    }

    @Test
    void retrieve() {

        EntityManager entityManager = JpaUtil.getEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        Employee employee1 = new Employee("employee1", 23);
        Employee employee2 = new Employee("employee2", 25);

        entityManager.persist(employee1);
        entityManager.persist(employee2);
        transaction.commit();

        System.out.println("Empleado creado: " + employee1);

        Employee employee1FromDb = entityManager.find(Employee.class, employee1.getId());

        System.out.println(employee1FromDb);
        entityManager.close();
    }

    @Test
    void update() {

        EntityManager entityManager = JpaUtil.getEntityManager();

        Employee emp1 = new Employee("employeeAActualizar", 23);
        emp1.setId(3L);
        emp1.setAge(24);

        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();
        entityManager.merge(emp1);
        transaction.commit();

        System.out.println(emp1);

    }

    @Test
    void delete() {

        EntityManager entityManager = JpaUtil.getEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        Employee e = new Employee("Empleado a borrar", 54);

        entityManager.persist(e);

        System.out.println("Empleado a borrar: " + e);
        entityManager.remove(e);
        transaction.commit();
    }
}
