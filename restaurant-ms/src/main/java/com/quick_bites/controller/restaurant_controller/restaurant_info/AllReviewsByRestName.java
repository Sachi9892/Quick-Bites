package com.quick_bites.controller.restaurant_controller.restaurant_info;


import com.quick_bites.dto.review_dto.ResponseReviewDto;
import com.quick_bites.services.restaurant_service.FindAllReviewByRest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/rest")
@AllArgsConstructor
public class AllReviewsByRestName {

    private final FindAllReviewByRest reviews;

    @GetMapping("/reviews")
    public ResponseEntity<Page<ResponseReviewDto>> allReviews
            (@RequestParam String name ,
             @RequestParam(defaultValue = "0") int page ,
             @RequestParam(defaultValue = "10") int size) {

        Pageable res = PageRequest.of(page , size);

        Page<ResponseReviewDto> allReview = reviews.findAllReview(name, res);

        return ResponseEntity.status(HttpStatus.OK).body(allReview);

    }




}
