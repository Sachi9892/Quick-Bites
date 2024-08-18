package com.quick_bites.controller.restaurant_controller;


import com.quick_bites.dto.dish_dto.ResponseDishDto;
import com.quick_bites.services.dishservice_public.DishesByRatingService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/rest/dish")
@AllArgsConstructor
public class DishesByRatingController {

    private final DishesByRatingService ratingService;


    @GetMapping("/rate")
    public ResponseEntity<List<ResponseDishDto>> allDishesByRating(
            @RequestParam double rating,
            @RequestParam(defaultValue = "0") int page ,
            @RequestParam(defaultValue = "10") int size) {

        PageRequest request = PageRequest.of(page, size);

        List<ResponseDishDto> responseDishDtos = ratingService.dishesByReview(rating, request);

        return ResponseEntity.status(HttpStatus.OK).body(responseDishDtos);
    }


}
