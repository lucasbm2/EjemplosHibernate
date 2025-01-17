package com.example;

import com.example.model.Book;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class EjemploMenu {

    public static void main(String[] args) {

        HibernateUtil.getSessionFactory();
        int opcion;
        do {
            System.out.println("\n--- Menú Principal ---");
            System.out.println("1. Mostrar libros");
            System.out.println("2. Actualizar libro");
            System.out.println("3. Insertar libro");
            System.out.println("4. Actualizar libro");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");
            opcion = introduceEntero();

            switch (opcion) {
                case 1 -> {
                    listarLibros();
                }
                case 2 -> {
                    actualizarLibro();
                }
                case 3 -> {

                }
                case 4 -> {

                }
                case 0 ->
                        System.out.println("Saliendo de la aplicación...");
                default ->
                        System.out.println("Opción no válida. Intenta de nuevo.");
            }
        } while (opcion != 0);
    }

    private static int introduceEntero() {
        int numero;
        Scanner scanner = new Scanner(System.in);
        try {
            numero = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Debes introducir un número natural válido.");
            return -1;
        }
        return numero;
    }

    // Metodo para listar libros almacenados en la base de datos.
    private static void listarLibros() {

        // Se abre una nueva sesión a partir de la SessionFactory.
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            // Inicia una nueva transacción en la sesión.
            tx = session.beginTransaction();

            // Ejecuta una consulta HQL para obtener todos los registros de la tabla Book.
            List libros = session.createQuery("FROM Book").list();

            // Itera sobre la lista de resultados y los imprime.
            for (Iterator iterator = libros.iterator(); iterator.hasNext();) {
                Book libro = (Book) iterator.next();
                System.out.println(libro);
            }

            // Confirma la transacción después de completar la operación.
            tx.commit();
        } catch (HibernateException e) {
            // Si ocurre un error, realiza un rollback de la transacción.
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            // Cierra la sesión para liberar los recursos.
            session.close();
        }

    }

    // Metodo para actualizar un registro de la tabla Book.
    public static void actualizarLibro() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Qué id de libro quieres modificar?");
        int id = introduceEntero();

        // Se abre una nueva sesión a partir de la SessionFactory.
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            // Inicia una nueva transacción en la sesión.
            tx = session.beginTransaction();

            // Busca el libro en la base de datos por su ID.
            Book libro = session.find(Book.class, id);

            if (libro != null) {
                // Muestra el libro que se va a actualizar.
                System.out.println("Va a actualizar el libro " + libro);

                // Solicita al usuario un nuevo nombre para el libro.
                System.out.println("¿Cuál será el nuevo nombre?");
                String nombre = scanner.nextLine();

                // Si el nombre no está vacío, actualiza el título del libro.
                if (!nombre.isEmpty()) libro.setTitle(nombre);

                // Solicita al usuario un nuevo precio para el libro.
                System.out.println("¿Cuál será el nuevo precio?");
                Double precio = scanner.nextDouble();

                // Limpia el buffer del scanner.
                scanner.nextLine();

                // Actualiza el precio del libro.
                libro.setPrice(precio);

                // Persiste los cambios realizados en el libro.
                session.persist(libro);

                // Confirma la transacción después de completar la operación.
                tx.commit();
            } else {
                System.out.println("El libro con id " + id + " no existe");
            }


        } catch (HibernateException e) {
            // Si ocurre un error, realiza un rollback de la transacción.
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            // Cierra la sesión y Scanner para liberar los recursos.
            session.close();
        }

    }
}
