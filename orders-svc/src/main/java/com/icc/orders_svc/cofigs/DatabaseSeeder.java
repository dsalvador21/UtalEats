package com.icc.orders_svc.cofigs;

import com.icc.orders_svc.models.Order;
import com.icc.orders_svc.repositories.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Component
public class DatabaseSeeder implements CommandLineRunner {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        if (orderRepository.count() == 0) {
            seedDatabase();
        }
    }

    private void seedDatabase() {
        Order order = new Order();
        order.setAccountId(1L);
        order.setProductsIds(List.of(1L, 2L, 3L, 4L, 5L));
        order.setOrderDate(LocalDateTime.now());

        Order order2 = new Order();
        order2.setAccountId(1L);
        order2.setProductsIds(List.of(6L, 7L, 8L, 13L, 15L));
        order2.setOrderDate(LocalDateTime.now().plusMinutes(10));

        Order order3 = new Order();
        order3.setAccountId(2L);
        order3.setProductsIds(List.of(12L, 15L, 17L));
        order3.setOrderDate(LocalDateTime.now().plusMinutes(20));

        orderRepository.saveAll(Arrays.asList(order, order2, order3));
    }

}
