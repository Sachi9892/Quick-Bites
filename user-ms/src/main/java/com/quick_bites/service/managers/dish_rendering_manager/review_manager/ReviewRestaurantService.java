package com.quick_bites.service.managers.dish_rendering_manager.review_manager;

import com.quick_bites.dto.reviewdto.GiveReviewDto;
import com.quick_bites.entity.User;
import com.quick_bites.entity.UserRestaurantReview;
import com.quick_bites.repository.RestaurantReviewRepository;
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
    private final RestaurantReviewRepository restReviewRepository;

    public String restaurantReview(GiveReviewDto reviewDto) {

        UserRestaurantReview review = new UserRestaurantReview();

        review.setRestaurantId(reviewDto.getId());
        review.setRating(reviewDto.getRating());
        review.setComment(reviewDto.getComment());
        review.setReviewTime(LocalDateTime.now());

        User user = userRepository.findById(reviewDto.getUserId())
                .orElseThrow(() -> new NoResourceFoundException("No user found"));
        review.setUser(user);

        UserRestaurantReview savedReview = restReviewRepository.save(review);

        log.info("Rest review saved: {}", savedReview);

        return restaurantClient.reviewRest(reviewDto);

    }

}
