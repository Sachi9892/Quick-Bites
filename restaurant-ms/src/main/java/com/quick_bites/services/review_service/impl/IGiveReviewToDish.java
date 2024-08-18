package com.quick_bites.services.review_service.impl;

import com.quick_bites.dto.review_dto.GiveReviewDto;
import com.quick_bites.entity.Dish;
import com.quick_bites.entity.Restaurant;
import com.quick_bites.entity.DishReview;
import com.quick_bites.exception.ResourceNotFoundException;
import com.quick_bites.repository.dish_repo.DishRepository;
import com.quick_bites.repository.restaurant_repo.RestaurantRepository;
import com.quick_bites.repository.review_repo.ReviewRepository;
import com.quick_bites.services.review_service.GiveReviewToDish;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class IGiveReviewToDish implements GiveReviewToDish {

    private final DishRepository dishRepository;

    private final ReviewRepository reviewRepository;

    private final RestaurantRepository restaurantRepository;


    @Override
    public void giveReview(GiveReviewDto reviewDto, String dishName) {

        //get dish
        Dish dish = dishRepository.findByName(dishName).orElseThrow(() -> new ResourceNotFoundException("No dish found with name" + dishName));

        DishReview dishReview = new DishReview();

        dishReview.setReviewTime(reviewDto.getReviewTime() != null ? reviewDto.getReviewTime() : LocalDateTime.now());
        dishReview.setDish(dish);
        dishReview.setRating(reviewDto.getRating());
        dishReview.setComment(reviewDto.getComment());


        reviewRepository.save(dishReview);

    }

}
