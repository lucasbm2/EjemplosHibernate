package com.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private Integer minAge;

    public Category() {
    }

    public Category(String name, Integer minAge) {
        this.name = name;
        this.minAge = minAge;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getMinAge() {
        return minAge;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMinAge(Integer minAge) {
        this.minAge = minAge;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", minAge=" + minAge +
                '}';
    }
}
