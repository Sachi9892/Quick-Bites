package com.quick_bites.controller.restaurant_controller.restaurant_info;


import com.quick_bites.dto.review_dto.ResponseReviewDto;
import com.quick_bites.services.restaurant_service.IFindAllReviewByRest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/restaurant")
@AllArgsConstructor
public class AllReviewsByRestName {

    private final IFindAllReviewByRest reviews;

    @GetMapping("/reviews")
    public ResponseEntity<List<ResponseReviewDto>> allReviews (@RequestParam String name ) {
        List<ResponseReviewDto> allReview = reviews.findAllReview(name);
        return ResponseEntity.status(HttpStatus.OK).body(allReview);
    }
}
