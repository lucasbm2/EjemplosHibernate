package com.example;

import com.example.model.Producto;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

/*
CRUD:
- Create
- Retrieve
- Update
- Delete
 */
public class ProductoTEST {

    void insertarProducto() {

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

    void recuperarProducto() {

        Session session = HibernateUtil.getSessionFactory().openSession();

        Transaction tx = session.beginTransaction();

        Producto producto1 = new Producto("fc6218a2-543e-4e5d-91c1-c623eda687e7", "Producto1", 1, BigDecimal.valueOf(100), Date.valueOf(LocalDate.now()), "CASA EN LAS PEDRONERAS");
//        session.persist(producto1);
//        tx.commit();

        Producto producto1FromDb = session.find(Producto.class, producto1.getId());

        System.out.println(producto1FromDb);

    }

    void actualizarProducto() {

        Session session = HibernateUtil.getSessionFactory().openSession();

        Producto producto1 = new Producto("fc6218a2-543e-4e5d-91c1-c623eda687e7", "Casa", 2, BigDecimal.valueOf(100), Date.valueOf(LocalDate.now()), "CASA EN EL PROVENCIO");
        producto1.setId("fc6218a2-543e-4e5d-91c1-c623eda687e7");

        Transaction tx = session.beginTransaction();
        session.merge(producto1);
        tx.commit();

        System.out.println(producto1);

    }

    //    @Test
//    public void recuperar() {
//        Session session = HibernateUtil.getSessionFactory().openSession();
//
//        Producto producto1 = session.find(Producto.class, "fc6218a2-543e-4e5d-91c1-c623eda687e7");
//        System.out.println("Mostrando producto: ");
//        System.out.println(producto1);
//        session.close();
//    }
//
    @Test
    void eliminarProducto() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

//        Producto producto1 = new Producto();
//        producto1.setId("f5dc2ec4-7c0e-48ae-9916-f5ffd3f4aca2");

        Producto producto1 = session.get(Producto.class, "f5dc2ec4-7c0e-48ae-9916-f5ffd3f4aca2");

//        Producto producto1 = new Producto( "Casa", 2, BigDecimal.valueOf(100), Date.valueOf(LocalDate.now()), "CASA EN EL PROVENCIO");
//
//        session.persist(producto1);
//
//        System.out.println("Producto creado para eliminar " + producto1);

        try {
            session.remove(producto1);
            tx.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        session.close();
    }

    void recuperarProductos() {

        Session session = HibernateUtil.getSessionFactory().openSession();

        List<Producto> productos = session.createQuery("from Producto", Producto.class).list();
        for (Producto producto : productos) {
            System.out.println(producto);
        }
        session.close();

    }
}
