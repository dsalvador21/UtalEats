package com.icc.ratings_svc.models;

import jakarta.persistence.*;

@Entity
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private short score;

    @Column(nullable = false)
    private String comment;

    @Column(nullable = false)
    private Long storeId; // Referencia al ID de la tienda en el servicio de catálogo

    public Rating(short score, String comment, Long storeId) {
        this.score = score;
        this.comment = comment;
        this.storeId = storeId;
    }

    public Rating() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public short getScore() {
        return score;
    }

    public void setScore(short score) {
        this.score = score;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }
}
