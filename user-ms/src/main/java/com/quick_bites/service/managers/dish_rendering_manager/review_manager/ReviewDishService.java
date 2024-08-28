package com.quick_bites.service.managers.dish_rendering_manager.review_manager;

import com.quick_bites.dto.reviewdto.GiveReviewDto;;
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
    public class ReviewDishService {

        private final DishClient dishClient;
        private final UserRepository userRepository;
        private final ReviewRepository reviewRepository;

        public ResponseEntity<String> dishReview(GiveReviewDto reviewDto , int dishId) {

            Review review = new Review();

            review.setRating(reviewDto.getRating());
            review.setComment(reviewDto.getComment());
            review.setReviewTime(LocalDateTime.now());

            User user = userRepository.findById(reviewDto.getUserId()).orElseThrow(() -> new NoResourceFoundException("No user found"));
            review.setUser(user);

            Review save = reviewRepository.save(review);
            log.info("Review:  {} " , save  );


            return dishClient.giveReview(reviewDto, dishId);
        }
}
