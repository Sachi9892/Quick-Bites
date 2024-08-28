package com.quick_bites.service.managers.dish_rendering_manager.review_manager;

import com.quick_bites.dto.reviewdto.GiveReviewDto;
import com.quick_bites.dto.reviewdto.ResponseReviewDto;
import com.quick_bites.entity.Review;
import com.quick_bites.entity.User;
import com.quick_bites.repository.ReviewRepository;
import com.quick_bites.repository.UserRepository;
import com.quick_bites.service.managers.dish_rendering_manager.feign_client.DishClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.resource.NoResourceFoundException;

import java.time.LocalDateTime;


@Service
@AllArgsConstructor
@Slf4j
public class ReviewRestaurantService {

    private final DishClient dishClient;
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;

    public ResponseEntity<String> restaurantReview(GiveReviewDto reviewDto , int restId) {

        Review review = new Review();

        review.setRating(reviewDto.getRating());
        review.setComment(reviewDto.getComment());
        review.setReviewTime(LocalDateTime.now());

        User user = userRepository.findById(reviewDto.getUserId()).orElseThrow(() -> new NoResourceFoundException("No user found"));
        review.setUser(user);

        reviewRepository.save(review);
        log.info("Inside rest review");

        return dishClient.reviewRest(reviewDto, restId);

    }

}
