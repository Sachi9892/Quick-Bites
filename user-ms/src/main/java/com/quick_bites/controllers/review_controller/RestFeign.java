package com.quick_bites.controllers.review_controller;

import com.quick_bites.dto.reviewdto.GiveReviewDto;
import com.quick_bites.service.managers.dish_rendering_manager.review_manager.ReviewDishService;
import com.quick_bites.service.managers.dish_rendering_manager.review_manager.ReviewRestaurantService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/user")
@AllArgsConstructor
public class RestFeign {

    private final ReviewRestaurantService reviewDishService;

    @PostMapping("/review/feign/rest")
    public ResponseEntity<String> doDishReview(@RequestBody GiveReviewDto reviewDto , @RequestParam Long restId) {

        String response = reviewDishService.restaurantReview(reviewDto, restId);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
