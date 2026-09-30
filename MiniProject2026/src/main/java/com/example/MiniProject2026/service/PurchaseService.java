package com.example.MiniProject2026.service;


import com.example.MiniProject2026.model.Product;
import com.example.MiniProject2026.model.Purchase;
import com.example.MiniProject2026.repo.ProductRepository;
import com.example.MiniProject2026.repo.PurchaseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class PurchaseService {

    @Autowired
    private PurchaseRepo purchaseRepository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public Purchase buy(int productId, int quantity) {
        if (quantity <= 0) {
            throw new RuntimeException("Quantity must be more than 0");
        }
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));

        if (!Boolean.TRUE.equals(product.getAvailable())) {
            throw new RuntimeException("Product not available");
        }
        if (product.getStock() < quantity) {
            throw new RuntimeException("Not enough stock");
        }

        product.setStock(product.getStock() - quantity);
        if (product.getStock() == 0) {
            product.setAvailable(false);
        }
        productRepository.save(product);

        Purchase purchase = new Purchase();
        purchase.setProductId(productId);
        purchase.setQuantity(quantity);
        purchase.setTotalPrice(product.getPrice() * quantity);
        purchase.setPurchaseDate(LocalDateTime.now());
        return purchaseRepository.save(purchase);
    }
}