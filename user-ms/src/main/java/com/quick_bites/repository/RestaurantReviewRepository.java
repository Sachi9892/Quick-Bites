package com.quick_bites.repository;

import com.quick_bites.entity.UserRestaurantReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantReviewRepository extends JpaRepository<UserRestaurantReview , Long> {

    List<UserRestaurantReview> findAllByUser_UserId(Long userId);

}
