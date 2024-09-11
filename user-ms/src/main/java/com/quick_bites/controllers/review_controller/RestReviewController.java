package com.quick_bites.controllers.review_controller;

import com.quick_bites.dto.reviewdto.GiveReviewDto;
import com.quick_bites.service.managers.dish_rendering_manager.review_manager.ReviewRestaurantService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/user")
@AllArgsConstructor
public class RestReviewController {

    private final ReviewRestaurantService reviewDishService;

    @PostMapping("/review/rest")
    public ResponseEntity<String> doDishReview(@RequestBody GiveReviewDto reviewDto) {

        String response = reviewDishService.restaurantReview(reviewDto);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
