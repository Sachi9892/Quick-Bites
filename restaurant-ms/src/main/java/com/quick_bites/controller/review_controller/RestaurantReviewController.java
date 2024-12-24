package com.quick_bites.controller.review_controller;

import com.quick_bites.dto.review_dto.GiveReviewDto;
import com.quick_bites.services.review_service.GiveReviewToRestaurant;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("restaurant/review")
@AllArgsConstructor
public class RestaurantReviewController {

    private final GiveReviewToRestaurant reviewToRestaurant;

    @PostMapping("/rest")
    public ResponseEntity<String> reviewRest(
            @RequestBody GiveReviewDto reviewDto) {

        reviewToRestaurant.giveReviewToRestaurant(reviewDto);
        return ResponseEntity.status(HttpStatus.OK).body("Thank you for giving review to restaurant");

    }
}
