package com.icc.ratings_svc.domain;

public class RatingDTO {
    private Long accountId;
    private Long storeId;
    private Double score;
    private String comment;

    public RatingDTO(){}

    public RatingDTO(Long storeId, Long accountId, Double score, String comment) {
        this.accountId = accountId;
        this.storeId = storeId;
        this.score = score;
        this.comment = comment;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
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

}
