package com.icc.orders_svc.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "order_table")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Column(nullable = false)
    private Long accountId;

    @ElementCollection
    private List<Long> productsIds;

    @Column(nullable = false)
    private LocalDateTime orderDate;

    public Order() {}

    public Order(Long orderId, Long accountId, List<Long> productsIds, LocalDateTime orderDate) {
        this.orderId = orderId;
        this.accountId = accountId;
        this.productsIds = productsIds;
        this.orderDate = orderDate;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long id) {
        this.orderId = id;
    }

    public List<Long> getProductsIds() {
        return productsIds;
    }

    public void setProductsIds(List<Long> productsIds) {
        this.productsIds = productsIds;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

}
