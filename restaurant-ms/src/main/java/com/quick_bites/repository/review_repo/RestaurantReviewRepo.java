package com.quick_bites.repository.review_repo;

import com.quick_bites.entity.RestaurantReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantReviewRepo extends JpaRepository<RestaurantReview , Long> {

}
