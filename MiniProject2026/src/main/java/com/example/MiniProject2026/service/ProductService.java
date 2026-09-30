package com.example.MiniProject2026.service;

import com.example.MiniProject2026.model.Product;
import com.example.MiniProject2026.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    public ProductRepository adminRepository;

    public List<Product> getAllProduct() {
        return adminRepository.findAll();
    }

    public Product getProductById(int id) {
        return adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }

    public Product getProductByName(String pname) {
        return adminRepository.findByPname(pname);
    }

    public Product addProduct(Product product) {
        return adminRepository.save(product);
    }

    public Product updateProduct(int id, Product updatedProduct) {
        Product existing = adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        existing.setPname(updatedProduct.getPname());
        existing.setPrice(updatedProduct.getPrice());
        existing.setStock(updatedProduct.getStock());
        existing.setCategory(updatedProduct.getCategory());
        return adminRepository.save(existing);
    }

    public void deleteProduct(int id) {
        adminRepository.deleteById(id);
    }
    public int getStock(int id) {
        Product product = adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        return product.getStock();
    }

    public String getCategory(int id) {
        Product product = adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        return product.getCategory();
    }

    public Product updateStock(int pid, int quantity) {
        Product product = adminRepository.findById(pid)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + pid));
        product.setStock(quantity);
        return adminRepository.save(product);
    }
    public Product setAvailability(int pid, boolean status) {
        Product product = adminRepository.findById(pid)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + pid));
        product.setAvailable(status);
        return adminRepository.save(product);
    }

    public List<Product> getLowStock(int threshold) {
        return adminRepository.findByStockLessThanEqual(threshold);
    }

    public List<Product> getUnavailable() {
        return adminRepository.findByAvailableFalse();
    }
}