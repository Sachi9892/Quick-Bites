package com.quick_bites.services.review_service.impl;

import com.quick_bites.dto.review_dto.GiveReviewDto;
import com.quick_bites.entity.Restaurant;
import com.quick_bites.entity.RestaurantReview;
import com.quick_bites.exception.ResourceNotFoundException;
import com.quick_bites.repository.restaurant_repo.RestaurantRepository;
import com.quick_bites.repository.review_repo.RestaurantReviewRepo;
import com.quick_bites.services.review_service.GiveReviewToRestaurant;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
@Slf4j
public class IGiveReviewToRestaurant implements GiveReviewToRestaurant {

    private final RestaurantReviewRepo reviewRepo;
    private final RestaurantRepository restaurantRepository;

    @Override
    public void giveReviewToRestaurant(GiveReviewDto reviewDto) {


        Long restId = reviewDto.getId();

        Restaurant restaurant = restaurantRepository.findById(restId).orElseThrow(() ->
                new ResourceNotFoundException("No restaurant found with id : " + restId));


        RestaurantReview review = new RestaurantReview();

        review.setRating(reviewDto.getRating());
        review.setComment(reviewDto.getComment());

        review.setRestaurant(restaurant);
        review.setReviewTime(LocalDateTime.now());

       review.setUserId(reviewDto.getUserId());

        log.info("Review created: id={}, rating={}, comment={}", review.getReviewId(), review.getRating(), review.getComment());

        RestaurantReview save = reviewRepo.save(review);

        log.info("in rest-ms review rest {} " , save);

    }
}
