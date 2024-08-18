package com.quick_bites.services.review_service.impl;

import com.quick_bites.dto.review_dto.GiveReviewDto;
import com.quick_bites.entity.Restaurant;
import com.quick_bites.entity.DishReview;
import com.quick_bites.entity.RestaurantReview;
import com.quick_bites.exception.ResourceNotFoundException;
import com.quick_bites.repository.restaurant_repo.RestaurantRepository;
import com.quick_bites.repository.review_repo.RestaurantReviewRepo;
import com.quick_bites.repository.review_repo.ReviewRepository;
import com.quick_bites.services.review_service.GiveReviewToRestaurant;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class IGiveReviewToRestaurant implements GiveReviewToRestaurant {

    private final RestaurantReviewRepo reviewRepo;
    private final RestaurantRepository restaurantRepository;

    @Override
    public void giveReviewToRestaurant(GiveReviewDto reviewDto, String restName) {

        Restaurant restaurant = restaurantRepository.findByRestaurantName(restName).orElseThrow(() ->
                new ResourceNotFoundException("No restaurant found with name : " + restName));


        RestaurantReview review = new RestaurantReview();

        review.setRating(reviewDto.getRating());
        review.setComment(reviewDto.getComment());
        review.setRestaurant(restaurant);
        review.setReviewTime(reviewDto.getReviewTime() != null ? reviewDto.getReviewTime() : LocalDateTime.now());

        reviewRepo.save(review);


    }
}
