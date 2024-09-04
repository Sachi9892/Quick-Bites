package com.quick_bites.repository.review_repo;

import com.quick_bites.entity.DishReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishReviewRepository extends JpaRepository<DishReview, Long> {

}
