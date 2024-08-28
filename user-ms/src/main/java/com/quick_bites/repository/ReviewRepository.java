package com.quick_bites.repository;

import com.quick_bites.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review , Long> {
}
