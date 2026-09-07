package com.example.MiniProject2026.controller;

import com.example.MiniProject2026.model.Review;
import com.example.MiniProject2026.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    public ReviewService reviewService;

    @PostMapping("/addReview")
    public Review addReview( @RequestBody Review review){
        return reviewService.addReview(review);
    }
    @GetMapping("/product/{productId}")
    public List<Review> getReviewByProduct(@PathVariable Long productId){
        return reviewService.getReviewByProduct(productId);
    }
    @GetMapping("/allReview")
    public List<Review> getAllReview(){
        return reviewService.getAllReview();
    }

}
