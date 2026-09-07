package com.example.MiniProject2026.repo;

import com.example.MiniProject2026.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
}
