package com.example.MiniProject2026.repo;

import com.example.MiniProject2026.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    Product findByPname(String pname);

}