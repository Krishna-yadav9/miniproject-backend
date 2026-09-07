package com.example.MiniProject2026.service;

import com.example.MiniProject2026.model.Order;
import com.example.MiniProject2026.model.OrderItem;  // ✅ must be same
import com.example.MiniProject2026.repo.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    public OrderRepository orderRepository;

    public List<Order> getOrderByUser(int userId) {
        return orderRepository.findByUserid(userId);
    }

    public Order getOrderId(int orderid) {
        return orderRepository.findById(orderid).orElseThrow(()->new RuntimeException("order not found with id: "+ orderid));
    }

    public Order placeOrder(int userId, List<OrderItem> items) {
        Order order= new Order();
        order.setUserid(userId);
        order.setOrderStatus("PLACED");
        order.setCreatedAt(LocalDateTime.now());

        double total =0;
        for(OrderItem item : items){
            item.setOrder(order);
            total+= item.getPriceAtPurchase()* item.getQuantity();
        }
        order.setTotalAmount(total);
        order.setOrderItems(items);
        return orderRepository.save(order);
    }

    public Order updateOrderStatus(int oid, String status) {
        Order order= orderRepository.findById(oid).orElseThrow(()->new RuntimeException("order not found with id: "+ oid));
        order.setOrderStatus(status);
        return orderRepository.save(order);
    }
}
