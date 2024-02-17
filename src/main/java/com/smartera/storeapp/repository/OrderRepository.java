package com.smartera.storeapp.repository;

import com.smartera.storeapp.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author yilmazsahin
 * @since 2/13/2024
 */

public interface OrderRepository extends JpaRepository<Order, Long> {
}
