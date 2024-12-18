package com.icc.orders_svc.domain;

import java.util.List;

public class OrderDTO {
    private Long accountId;
    private Long storeId;
    private List<Long> productsIds;

    public OrderDTO() {}

    public Long getAccountId() {
        return accountId;
    }

    public List<Long> getProductsIds() {
        return productsIds;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public void setProductsIds(List<Long> productsIds) {
        this.productsIds = productsIds;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

}
