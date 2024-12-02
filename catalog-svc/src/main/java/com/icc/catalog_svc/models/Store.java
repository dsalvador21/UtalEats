package com.icc.catalog_svc.models;

import jakarta.persistence.*;

@Entity
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String logoUrl;

    @Column(nullable = false)
    private Double rating;

    @Column(nullable = false)
    private String city;

    public Store(Long id, String name, String category, String logoUrl, Double rating, String city) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.logoUrl = logoUrl;
        this.rating = rating;
        this.city = city;
    }

    public Store() {}

    public Long getId() {
        return id;
    }

    public Store setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Store setName(String name) {
        this.name = name;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public Store setCategory(String category) {
        this.category = category;
        return this;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public Store setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
        return this;
    }

    public Double getRating() {
        return rating;
    }

    public Store setRating(Double rating) {
        this.rating = rating;
        return this;
    }

    public String getCity() {
        return city;
    }

    public Store setCity(String city) {
        this.city = city;
        return this;
    }

}
