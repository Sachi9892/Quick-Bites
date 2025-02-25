package com.quick_bites.controllers.fetch_dishes;


import com.quick_bites.dto.dishdto.ResponseDishDto;
import com.quick_bites.service.managers.dish_rendering_manager.search.SearchAndSortApi;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @implNote = this controller fetching dishes with help of FEIGN CLIENT
 */


@Controller
@RequestMapping("/user")
@AllArgsConstructor
@Slf4j
public class SearchDishesController {

    private final SearchAndSortApi searchAndSortApi;

    @GetMapping("/search")
    public ResponseEntity<List<ResponseDishDto>> getFilteredAndSortedDishes(
            @RequestParam("query") String query,
            @RequestParam(value = "minPrice", required = false , defaultValue = "0") Double minPrice,
            @RequestParam(value = "maxPrice", required = false) Double maxPrice,
            @RequestParam(value = "minRating", required = false) Double minRating,
            @RequestParam(value = "minDistance", required = false , defaultValue = "0") Double minDistance,
            @RequestParam(value = "maxDistance", required = false , defaultValue = "5") Double maxDistance,
            @RequestParam(value = "userLatitude", required = false) Double userLatitude,
            @RequestParam(value = "userLongitude", required = false) Double userLongitude,
            @RequestParam(value = "dishType", required = false) String dishType,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "ascending", required = false) boolean ascending
    ) {


        List<ResponseDishDto> responseDishDtos = searchAndSortApi.searchAndSortDishes(
                query, minPrice, maxPrice, minRating, minDistance,
                maxDistance, userLatitude, userLongitude, dishType,sortBy, ascending
        );

        log.info("No of dishes from controller : {} " , responseDishDtos.size());

        return ResponseEntity.status(HttpStatus.OK).body(responseDishDtos);

    }


}
