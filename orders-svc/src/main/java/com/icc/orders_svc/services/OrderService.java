package com.icc.orders_svc.services;

import com.icc.orders_svc.models.Order;
import com.icc.orders_svc.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public Order register(Long accountId, Long storeId, List<Long> productsIds) {
        Order order = new Order();
        order.setAccountId(accountId);
        order.setStoreId(storeId);
        order.setProductsIds(productsIds);
        order.setOrderDate(LocalDateTime.now());
        orderRepository.save(order);
        return order;
    }

    public Optional<List<Order>> getOrdersByAccount(Long accountId) {
        return orderRepository.findOrdersByAccountId(accountId);
    }

}
