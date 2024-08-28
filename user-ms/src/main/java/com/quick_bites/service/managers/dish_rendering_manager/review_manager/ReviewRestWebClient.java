package com.quick_bites.service.managers.dish_rendering_manager.review_manager;

import com.quick_bites.dto.reviewdto.GiveReviewDto;
import com.quick_bites.entity.Review;
import com.quick_bites.entity.User;
import com.quick_bites.repository.ReviewRepository;
import com.quick_bites.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.resource.NoResourceFoundException;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
@Slf4j
public class ReviewRestWebClient {

    private final WebClient webClient = WebClient.builder()
            .baseUrl("http://RESTAURANT-MS")
            .build();

    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;

    public void restReview(GiveReviewDto reviewDto , int restId) {

        // Create a new review
        Review review = new Review();
        review.setRating(reviewDto.getRating());
        review.setComment(reviewDto.getComment());
        review.setReviewTime(LocalDateTime.now());


        // Fetch user from the database
        User user = userRepository.findById(reviewDto.getUserId())
                .orElseThrow(() -> new NoResourceFoundException("No user found"));
        review.setUser(user);


        // Save the review
        Review savedReview = reviewRepository.save(review);
        log.info("Review: {}", savedReview);


        // Send the review to the Dish service using WebClient
        webClient.post()
                .uri(uriBuilder -> uriBuilder.path("/review/rest")
                        .queryParam("restId", restId)
                        .build())
                .bodyValue(reviewDto)
                .retrieve()
                .bodyToMono(Void.class)
                .doOnSuccess(response -> log.info("Review sent successfully"))
                .doOnError(error -> log.error("Error sending review: ", error))
                .subscribe();

    }
}