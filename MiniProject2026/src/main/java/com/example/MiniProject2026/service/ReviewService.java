package com.example.MiniProject2026.service;

import com.example.MiniProject2026.model.Review;
import com.example.MiniProject2026.repo.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    @Autowired
    public ReviewRepository reviewRepository;

    public Review addReview(Review review){
        review.setCreatedAt(java.time.LocalDateTime.now());
        return  reviewRepository.save(review);
    }
    public List<Review> getReviewByProduct(Long pid){
        return reviewRepository.findByProductPid(pid);
    }
    public List<Review> getAllReview() {
        return reviewRepository.findAll();
    }
}
