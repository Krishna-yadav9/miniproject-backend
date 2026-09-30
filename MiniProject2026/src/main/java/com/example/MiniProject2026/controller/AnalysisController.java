package com.example.MiniProject2026.controller;

import com.example.MiniProject2026.dto.ProductDemand;
import com.example.MiniProject2026.model.Product;
import com.example.MiniProject2026.service.AnalysisService;
import com.example.MiniProject2026.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/analysis")
public class AnalysisController {

    @Autowired
    private AnalysisService analysisService;

    @Autowired
    private ProductService productService;

    @GetMapping("/top-selling")
    public List<ProductDemand> topSelling(@RequestParam(defaultValue = "5") int limit) {
        return analysisService.getTopSelling(limit);
    }

    @GetMapping("/demand/{pid}")
    public Long demand(@PathVariable int pid) {
        return analysisService.getDemand(pid);
    }

    @GetMapping("/low-stock")
    public List<Product> lowStock(@RequestParam(defaultValue = "10") int threshold) {
        return productService.getLowStock(threshold);
    }

    @GetMapping("/unavailable")
    public List<Product> unavailable() {
        return productService.getUnavailable();
    }
}