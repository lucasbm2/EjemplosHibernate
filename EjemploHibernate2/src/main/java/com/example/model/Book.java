package com.example.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    private Double price;

    @Column(name = "num_pages")
    private Integer numPages;

    private Boolean published;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "author_id")
    private Author author;

    @ManyToMany
    @JoinTable(
            name = "book_category",
            joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id")
    )
    private Set<Category> categories = new HashSet<>();

    public Book() {
    }

    public Book(String title, Double price, Integer numPages, Boolean published, Author author) {
        this.title = title;
        this.price = price;
        this.numPages = numPages;
        this.published = published;
        this.author = author;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getNumPages() {
        return numPages;
    }

    public Boolean getPublished() {
        return published;
    }

    public Author getAuthor() {
        return author;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setNumPages(Integer numPages) {
        this.numPages = numPages;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", numPages=" + numPages +
                ", published=" + published +
                '}';
    }
}
