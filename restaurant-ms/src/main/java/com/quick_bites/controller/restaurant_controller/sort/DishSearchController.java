package com.quick_bites.controller.restaurant_controller.sort;


import com.quick_bites.dto.dish_dto.ResponseDishDto;
import com.quick_bites.entity.DishType;
import com.quick_bites.services.dishservice_public.IDishFilterService;
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
public class DishSortingController {

    private final IDishFilterService filterService;

    @GetMapping("/sort")
    public ResponseEntity<List<ResponseDishDto>> sortDishes(
            @RequestParam(value = "query", required = false) String query,
            @RequestParam(value = "minPrice", required = false) Double minPrice,
            @RequestParam(value = "maxPrice", required = false) Double maxPrice,
            @RequestParam(value = "minRating", required = false) Double minRating,
            @RequestParam(value = "minDistance", required = false , defaultValue = "0") Double minDistance,
            @RequestParam(value = "maxDistance", required = false , defaultValue = "5") Double maxDistance,
            @RequestParam(value = "userLatitude" , required = false) Double userLatitude,
            @RequestParam(value = "userLongitude" , required = false) Double userLongitude,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "dishType" , required = false) DishType dishType,
            @RequestParam(value = "ascending", defaultValue = "true") boolean ascending)
    {



        List<ResponseDishDto> filteredAndSortedDishes = filterService.getFilteredAndSortedDishes(
                query,
                minPrice,
                maxPrice,
                minRating,
                minDistance,
                maxDistance,
                userLatitude,
                userLongitude,
                dishType,
                sortBy,
                ascending);

        return ResponseEntity.status(HttpStatus.OK).body(filteredAndSortedDishes);
    }

}
