package com.example.MiniProject2026.controller;

import com.example.MiniProject2026.model.Order;
import com.example.MiniProject2026.model.OrderItem;
import com.example.MiniProject2026.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/getorder")
public class OrderController {
    @Autowired
    public OrderService orderService;


    @GetMapping("/user/{userId}")
    public List<Order> getOrderByUser(@PathVariable int userId){
     return orderService.getOrderByUser(userId);
    }
    @GetMapping("/{oid}")
    public Order getOrderId(@PathVariable int oid){
        return orderService.getOrderId(oid);
    }
    @PostMapping("/place/{userId}")
    public Order placeOrder(@PathVariable int userId,@RequestBody List<OrderItem> items){
        return orderService.placeOrder(userId , items);
    }
    @PutMapping("/status/{oid}")
    public Order updateOrderStatus(@PathVariable int oid,@RequestBody String status){
        return orderService.updateOrderStatus(oid, status);
    }
}
