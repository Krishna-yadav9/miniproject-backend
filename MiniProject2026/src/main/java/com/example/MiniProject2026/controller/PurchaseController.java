package com.example.MiniProject2026.controller;

import com.example.MiniProject2026.model.Purchase;
import com.example.MiniProject2026.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @PostMapping("/buy")
    public Purchase buy(@RequestParam int productId, @RequestParam int quantity) {
        return purchaseService.buy(productId, quantity);
    }
}