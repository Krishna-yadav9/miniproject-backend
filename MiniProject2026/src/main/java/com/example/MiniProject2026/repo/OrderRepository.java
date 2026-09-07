package com.example.MiniProject2026.repo;

import com.example.MiniProject2026.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByUserid(int userid);
}
