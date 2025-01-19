package com.example;

import com.example.model.Employee;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Test;

/*
CRUD:
- Create
- Retrieve
- Update
- Delete
 */
public class HibernateTest {

    @Test
    void persist() {

        Session session = HibernateUtil.getSessionFactory().openSession();

        Transaction tx = session.beginTransaction();

        Employee employee1 = new Employee("employee1", 23);
        Employee employee2 = new Employee("employee2", 25);

        session.persist(employee1);
        session.persist(employee2);

        tx.commit();

        session.close();
    }

    @Test
    void retrieve() {

        Session session = HibernateUtil.getSessionFactory().openSession();

        Transaction tx = session.beginTransaction();

        Employee employee1 = new Employee("employee1", 23);
        Employee employee2 = new Employee("employee2", 25);

        session.persist(employee1);
        session.persist(employee2);
        tx.commit();

        System.out.println("Empleado creado: " + employee1);
        Employee employee1FromDb = session.find(Employee.class, employee1.getId());

        System.out.println(employee1FromDb);

    }


    @Test
    void update() {

        Session session = HibernateUtil.getSessionFactory().openSession();

        Employee emp1 = new Employee("employee1", 23);
        emp1.setId(3L);
        emp1.setAge(24);

        Transaction tx = session.beginTransaction();
        session.merge(emp1);
        tx.commit();

        System.out.println(emp1);

    }

    @Test
    void delete() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Employee e = new Employee("employee1", 23);

        session.persist(e);

        System.out.println("Empleado creado para eliminar: " + e);
        session.remove(e);

        tx.commit();
    }
}
