package com.quick_bites.repository;

import com.quick_bites.entity.UserDishReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DishReviewRepository extends JpaRepository<UserDishReview, Long> {

    List<UserDishReview> findAllByUser_UserId(Long userId);

}
