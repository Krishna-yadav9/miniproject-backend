package com.example.MiniProject2026.repo;

import com.example.MiniProject2026.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByProductPid(Long pid);
}
