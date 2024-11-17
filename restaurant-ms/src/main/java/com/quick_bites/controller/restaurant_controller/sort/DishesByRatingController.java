package com.quick_bites.controller.restaurant_controller.sort;


import com.quick_bites.dto.dish_dto.ResponseDishDto;
import com.quick_bites.services.dishservice_public.IDishesByRatingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/dishes")
@AllArgsConstructor
public class DishesByRatingController {

    private final IDishesByRatingService ratingService;

    @GetMapping("/by-rating")
    public ResponseEntity<List<ResponseDishDto>> dishesByRating(@RequestParam Double minRating) {

        List<ResponseDishDto> dishes = ratingService.dishesByReview(minRating);

        return ResponseEntity.status(HttpStatus.OK).body(dishes);

    }

}
