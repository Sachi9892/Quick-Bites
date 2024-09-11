package com.quick_bites.controllers.user_controller;


import com.quick_bites.dto.reviewdto.ResponseReviewDto;
import com.quick_bites.service.user_profile.IFetchDishReviews;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/user")
@AllArgsConstructor
public class UserDishReviewController {

    private final IFetchDishReviews dishReviewsService;

    @GetMapping("/profile/dish/reviews")
    public ResponseEntity<List<ResponseReviewDto>> fetchAllDishReviews(@RequestParam Long userId) {

        List<ResponseReviewDto> dishReviews = dishReviewsService.fetchDishReviewByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(dishReviews);

    }

}
