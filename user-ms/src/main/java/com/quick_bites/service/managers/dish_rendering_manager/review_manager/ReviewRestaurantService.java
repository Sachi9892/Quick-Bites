package com.quick_bites.service.managers.dish_rendering_manager.review_manager;

import com.quick_bites.dto.reviewdto.GiveReviewDto;
import com.quick_bites.entity.Review;
import com.quick_bites.entity.User;
import com.quick_bites.repository.ReviewRepository;
import com.quick_bites.repository.UserRepository;
import com.quick_bites.service.managers.dish_rendering_manager.feign_client.RestaurantClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.resource.NoResourceFoundException;

import java.time.LocalDateTime;


@Service
@AllArgsConstructor
@Slf4j
public class ReviewRestaurantService {

    private final RestaurantClient restaurantClient;
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;

    public String restaurantReview(GiveReviewDto reviewDto , Long restId) {

        Review review = new Review();

        review.setRating(reviewDto.getRating());
        review.setComment(reviewDto.getComment());
        review.setReviewTime(LocalDateTime.now());

        User user = userRepository.findById(reviewDto.getUserId()).orElseThrow(() -> new NoResourceFoundException("No user found"));
        review.setUser(user);

        reviewRepository.save(review);
        log.info("Inside rest review");

        return restaurantClient.reviewRest(reviewDto, restId);

    }

}
