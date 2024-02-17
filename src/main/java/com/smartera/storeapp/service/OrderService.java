package com.smartera.storeapp.service;

import com.smartera.storeapp.entity.Customer;
import com.smartera.storeapp.entity.Order;
import com.smartera.storeapp.repository.CustomerRepository;
import com.smartera.storeapp.repository.OrderRepository;
import org.springframework.aop.support.Pointcuts;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

/**
 * @author yilmazsahin
 * @since 2/13/2024
 */
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    public OrderService(OrderRepository orderRepository,
                        CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public Order updateOrder(Long customerId, Long orderId, Order updatedOrder) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order != null && customer != null) {
            if (order.getCustomer().getId().equals(customerId)) {
                updatedOrder.setId(orderId);
                updatedOrder.setCustomer(customer);
                return orderRepository.save(updatedOrder);
            }
        }
        return null;
    }

    public boolean deleteOrderById(Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public Order saveOrder(Long customerId, Order order) {
        Customer customer = customerRepository.findById(customerId).orElse(null);

        if (customer != null) {


            order.setCustomer(customer);
            order.setOrderTrackingNumber(UUID.randomUUID().toString());
            return orderRepository.save(order);
        }

        return null;
    }

    public boolean createOrder(Long customerId, String authorizationLevel) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer != null) {
            if ("CUSTOMER".equals(authorizationLevel) || "ADMIN".equals(authorizationLevel)) {
                return true;
            }
        }
        return false;
    }
}
