package com.quick_bites.controllers.review_controller;


import com.quick_bites.dto.reviewdto.GiveReviewDto;
import com.quick_bites.service.managers.dish_rendering_manager.review_manager.ReviewDishService;
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
public class DishFeign {

    private final ReviewDishService reviewDishService;

    @PostMapping("/review/feign/dish")
    public ResponseEntity<String>
    doRestReview(@RequestBody GiveReviewDto reviewDto ,
             @RequestParam int dishId) {

        reviewDishService.dishReview(reviewDto, dishId);

        return ResponseEntity.status(HttpStatus.OK).body("Thank you for the feign client review dish");
    }
}
