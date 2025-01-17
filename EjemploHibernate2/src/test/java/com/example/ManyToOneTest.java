package com.example;

import com.example.model.Address;
import com.example.model.Author;
import com.example.model.Book;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class ManyToOneTest {

    @Test
    void manyToOne() {

        insertData();
        var session = HibernateUtil.getSessionFactory().openSession();

        var book1 = session.find(Book.class, 1L);
        System.out.println(book1.getAuthor());

        var book3 = session.find(Book.class, 3L);
        System.out.println(book1);
        System.out.println(book3.getAuthor());
    }

    void insertData() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        Address address = new Address("Calle Mayor, 1","Pamplona","España");
        Address address2 = new Address("Calle Mayor, 2","Madrid","España");

        Author author = new Author(
                "Garcilaso",
                "garcilaso@poeta.com",
                LocalDate.of(1536,10,14)
        );
        author.setAddress(address);

        Author author2 = new Author(
                "Petrarca",
                "petrarca@poeta.com",
                LocalDate.of(1304,7,19)
        );
        author2.setAddress(address2);

        session.persist(address);
        session.persist(address2);

        session.persist(author);
        session.persist(author2);

        Book book1 = new Book("Las obras de Boscán", 19.90, 450, true, author);
        Book book2 = new Book("Las obras de Roldán", 19.90, 157, false, author);
        Book book3 = new Book("Las obras de Gutierrez", 19.90, 123, true, author2);
        Book book4 = new Book("Las obras de Sánchez", 19.90, 987, false, author2);

        session.persist(book1);
        session.persist(book2);
        session.persist(book3);
        session.persist(book4);

        session.getTransaction().commit();
    }
}
