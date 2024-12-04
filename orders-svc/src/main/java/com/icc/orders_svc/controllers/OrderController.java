package com.icc.orders_svc.controllers;

import com.icc.orders_svc.domain.OrderDTO;
import com.icc.orders_svc.models.Order;
import com.icc.orders_svc.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @CrossOrigin(origins = "*")
    @PostMapping("")
    public ResponseEntity<Order> register(@RequestBody OrderDTO orderDTO) {
        Order savedOrder = orderService.register(
                orderDTO.getAccountId(),
                orderDTO.getProductsIds()
        );
        return ResponseEntity.ok().body(savedOrder);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("")
    public ResponseEntity<List<Order>> getOrders(@RequestParam("accountId") Long accountId) {
        Optional<List<Order>> orders = orderService.getOrdersByAccount(accountId);

        if (orders.isPresent()) {
            return ResponseEntity.ok().body(orders.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
