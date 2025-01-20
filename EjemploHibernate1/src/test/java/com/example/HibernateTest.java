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

        Employee employee1 = new Employee("Lucas", 24);
        // MERGE ES PARA ACTUALIZAR SI EXISTE O CREAR SI NO EXISTE
//        session.merge(employee1);
        Employee employee2 = new Employee("Rayo", 21);

        System.out.println("Empleado antes de persistir X" + employee1.toString());
        System.out.println("Empleado antes de persistir X+1" + employee2.toString());

        session.persist(employee1);
        session.persist(employee2);

        System.out.println("Empleado despues de persistir X" + employee1.toString());
        System.out.println("Empleado despues de persistir X+1" + employee2.toString());

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

        Employee employee1FromDb = session.find(Employee.class, employee1.getId());
        Employee employee2FromDb = session.find(Employee.class, employee2.getId());

        System.out.println(employee1FromDb);
        System.out.println(employee2FromDb);

    }


    @Test
    void update() {

        Session session = HibernateUtil.getSessionFactory().openSession();

        Employee emp1 = new Employee("Diego", 21);
        emp1.setId(1L);
        emp1.setAge(30);

        Transaction tx = session.beginTransaction();
        session.merge(emp1);
        tx.commit();

        System.out.println(emp1);

    }

//    @Test
//    void delete() {
//
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        Transaction tx = session.beginTransaction();
//
//        Employee empPrueba = new Employee("employee1", 23);
//
//        session.persist(empPrueba);
//
//        System.out.println("Empleado creado para eliminar: " + empPrueba);
//        session.remove(empPrueba);
//
//        tx.commit();
//    }
}
