package com.example.rain.repository;

import com.example.rain.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem,String> {

    OrderItem findByCode(String code);
}
