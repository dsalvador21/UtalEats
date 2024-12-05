package com.icc.ratings_svc.domain;

public class RatingDTO {
    private Long id;
    private Long storeId;
    private Long userId;
    private Double score;
    private String comment;

    public RatingDTO(){

    }
    public RatingDTO(Long id, Long storeId, Long userId, Double score, String comment) {
        this.id = id;
        this.storeId = storeId;
        this.userId = userId;
        this.score = score;
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    @Override
    public String toString() {
        return "RatingDTO{" +
                "id=" + id +
                ", storeId=" + storeId +
                ", userId=" + userId +
                ", score=" + score +
                ", comment='" + comment + '\'' +
                '}';
    }
}
