package com.quick_bites.services.review_service.impl;

import com.quick_bites.dto.review_dto.GiveReviewDto;
import com.quick_bites.entity.Dish;
import com.quick_bites.entity.DishReview;
import com.quick_bites.exception.ResourceNotFoundException;
import com.quick_bites.repository.dish_repo.DishRepository;
import com.quick_bites.repository.review_repo.DishReviewRepository;
import com.quick_bites.services.review_service.GiveReviewToDish;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
@Slf4j
public class IGiveReviewToDish implements GiveReviewToDish {

    private final DishRepository dishRepository;

    private final DishReviewRepository dishReviewRepository;

    @Override
    public void giveReview(GiveReviewDto reviewDto) {

        Long dishId = reviewDto.getId();

        //get dish
        Dish dish = dishRepository.findById(dishId)
                .orElseThrow(() -> new ResourceNotFoundException("No dish found with id" + dishId));

        DishReview dishReview = new DishReview();

        dishReview.setReviewTime(LocalDateTime.now());
        dishReview.setDish(dish);
        dishReview.setRating(reviewDto.getRating());
        dishReview.setComment(reviewDto.getComment());

        dishReview.setUserId(reviewDto.getUserId());

        log.info("Review created: id={}, rating={}, comment={}", dishReview.getReviewId(), dishReview.getRating(), dishReview.getComment());


        DishReview save = dishReviewRepository.save(dishReview);

        log.info("Review in rest to dish : {} " , save);

    }

}
