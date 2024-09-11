package com.quick_bites.controller.review_controller;

import com.quick_bites.dto.review_dto.GiveReviewDto;
import com.quick_bites.services.review_service.GiveReviewToDish;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/review")
public class DishReviewController {

    private final GiveReviewToDish reviewToDish;

    @PostMapping("/dish")
    public ResponseEntity<String> giveReview(@RequestBody GiveReviewDto reviewDto) {

        reviewToDish.giveReview(reviewDto);
        return ResponseEntity.status(HttpStatus.OK).body("Thanks you for giving review to dish");

    }

}
