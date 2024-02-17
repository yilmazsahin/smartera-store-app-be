package com.smartera.storeapp.controller;


import com.smartera.storeapp.entity.Order;
import com.smartera.storeapp.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * @author yilmazsahin
 * @since 2/14/2024
 */

@RestController
@RequestMapping("/api/orders")

public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/save/{customerId}")
    public ResponseEntity<Order> createOrder(@PathVariable Long customerId, @RequestBody Order order) {
        order.setOrderTrackingNumber(generateOrderTrackingNumber());

        Order createdOrder = orderService.saveOrder(customerId, order);
        return ResponseEntity.ok(createdOrder);
    }

    @GetMapping("/getOrderById/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        if (order != null) {
            return ResponseEntity.ok(order);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getAllOrders")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PutMapping("/updateOrderById/{customerId}/{id}")
    public ResponseEntity<Order> updateOrderById(@PathVariable Long customerId, @PathVariable Long id, @RequestBody Order order) {

        Order updatedOrder = orderService.updateOrder(customerId, id, order);
        if (updatedOrder != null) {
            return ResponseEntity.ok(updatedOrder);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deleteOrderById/{id}")
    public ResponseEntity<?> deleteOrderById(Long id) {
        boolean deletedOrder = orderService.deleteOrderById(id);
        if (deletedOrder) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }
    private String generateOrderTrackingNumber() {
        return UUID.randomUUID().toString();
    }
}



