package com.example.MiniProject2026.controller;

import com.example.MiniProject2026.model.Product;
import com.example.MiniProject2026.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class ProductController {

    @Autowired
    public ProductService productService;

    @GetMapping("/allproduct")
    public List<Product> getAllProduct() {
        return productService.getAllProduct();
    }

    @GetMapping("/product/id/{id}")
    public Product getProductById(@PathVariable int id) {
        return productService.getProductById(id);
    }

    @GetMapping("/product/name/{name}")
    public Product getProductByName(@PathVariable String name) {
        return productService.getProductByName(name);
    }

    @PostMapping("/addproduct")
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @PutMapping("/update/{id}")
    public Product updateProduct(@PathVariable int id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
    }
    @GetMapping("/product/stock/{id}")
    public int getStock(@PathVariable int id) {
        return productService.getStock(id);
    }

    @GetMapping("/product/category/{id}")
    public String getCategory(@PathVariable int id) {
        return productService.getCategory(id);
    }
}