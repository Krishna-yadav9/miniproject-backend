
package com.example.MiniProject2026.service;

import com.example.MiniProject2026.dto.ProductDemand;
import com.example.MiniProject2026.repo.PurchaseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnalysisService {

    @Autowired
    private PurchaseRepo purchaseRepository;

    public List<ProductDemand> getTopSelling(int limit) {
        return purchaseRepository.findTopSelling(PageRequest.of(0, limit));
    }

    public Long getDemand(int pid) {
        return purchaseRepository.totalSoldByProduct(pid);
    }
}