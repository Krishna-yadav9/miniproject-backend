package com.example.MiniProject2026.repo;


import com.example.MiniProject2026.dto.ProductDemand;
import com.example.MiniProject2026.model.Purchase;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRepo extends JpaRepository<Purchase, Long> {

    @Query("SELECT new com.example.MiniProject2026.dto.ProductDemand(p.pid, p.pname, SUM(pu.quantity)) " +
            "FROM Purchase pu, Product p WHERE pu.productId = p.pid " +
            "GROUP BY p.pid, p.pname ORDER BY SUM(pu.quantity) DESC")
    List<ProductDemand> findTopSelling(Pageable pageable);

    @Query("SELECT COALESCE(SUM(pu.quantity), 0) FROM Purchase pu WHERE pu.productId = :pid")
    Long totalSoldByProduct(int pid);
}