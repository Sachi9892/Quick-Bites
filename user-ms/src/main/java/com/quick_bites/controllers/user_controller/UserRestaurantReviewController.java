package com.quick_bites.controllers.user_controller;


import com.quick_bites.dto.reviewdto.ResponseReviewDto;
import com.quick_bites.service.user_profile.IFetchRestaurantReviews;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/user")
public class UserRestaurantReviewController {

    private final IFetchRestaurantReviews restaurantReviews;

    @GetMapping("/profile/rest/reviews")
    public ResponseEntity<List<ResponseReviewDto>> fetchAllDishReviews(@RequestParam Long userId) {

        List<ResponseReviewDto> restReviews = restaurantReviews.fetchRestReviewByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(restReviews);

    }
}
