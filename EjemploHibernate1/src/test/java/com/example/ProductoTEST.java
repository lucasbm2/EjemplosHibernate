package com.example;

import com.example.model.Producto;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;

/*
CRUD:
- Create
- Retrieve
- Update
- Delete
 */
public class ProductoTEST {

    @Test
    void persist() {

        Session session = HibernateUtil.getSessionFactory().openSession();

        Transaction tx = session.beginTransaction();

        Producto producto1 = new Producto();
        producto1.setNombre("Casa");
        producto1.setCantidad(1);
        producto1.setPrecioUnitario(100000);
        producto1.setFechaCreacion(Date.valueOf(LocalDate.now()));
        producto1.setDescripcion("CASA EN LAS PEDRONERAS");


        System.out.println("Producto antes de persistir X" + producto1.toString());

        session.persist(producto1);

        System.out.println("Producto despues de persistir X" + producto1.toString());

        tx.commit();

        session.close();
    }

    @Test
    void retrieve() {

        Session session = HibernateUtil.getSessionFactory().openSession();

        Transaction tx = session.beginTransaction();

        Producto producto1 = new Producto("Producto1", 1, 100000, Date.valueOf(LocalDate.now()), "CASA EN LAS PEDRONERAS");

        session.persist(producto1);
        tx.commit();

        Producto producto1FromDb = session.find(Producto.class, producto1.getId());

        System.out.println(producto1FromDb);

    }


    @Test
    void update() {

        Session session = HibernateUtil.getSessionFactory().openSession();

        Producto producto1 = new Producto("Casa", 2, 100000, Date.valueOf(LocalDate.now()), "CASA EN EL PROVENCIO");
        producto1.setId(1L);

        Transaction tx = session.beginTransaction();
        session.merge(producto1);
        tx.commit();

        System.out.println(producto1);

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
